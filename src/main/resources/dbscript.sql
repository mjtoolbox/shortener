------------------------------------
----------- URL Shorten ------------
------------------------------------
CREATE SEQUENCE public.urlshorten_url_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.urlShorten (
    url_id integer NOT NULL DEFAULT nextval('urlshorten_url_id_seq'),
    shortUrl character varying(200) NOT NULL,
    originalUrl character varying(2048) NOT NULL,
  	click integer NOT NULL,
    createdBy character varying(200) NOT NULL,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT url_id_pk PRIMARY KEY (url_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.urlShorten OWNER TO postgres;
-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when student table is deleted, automatically delete this sequence.
ALTER SEQUENCE public.urlshorten_url_id_seq OWNED BY public.urlShorten.url_id;
-- END OF URLShorten --

------------------------------------
----------- URL Log ------------
------------------------------------
CREATE SEQUENCE public.urlshorten_url_log_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.urlLog (
    url_log_id integer NOT NULL DEFAULT nextval('urlshorten_url_log_id_seq'),
    url_id integer NOT NULL,
    shortUrl character varying(200) NOT NULL,
    last_accessed timestamp without time zone NOT NULL,
    CONSTRAINT url_log_id_pk PRIMARY KEY (url_log_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.urlLog OWNER TO postgres;
-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when student table is deleted, automatically delete this sequence.
ALTER SEQUENCE public.urlshorten_url_log_id_seq OWNED BY public.urlLog.url_log_id;
-- END OF URLShorten --

----------------------------------------------------
----------------- DEFINE RELATIONSHIP --------------
----------------------------------------------------
ALTER TABLE ONLY public.urlLog
    ADD CONSTRAINT url_id_pk FOREIGN KEY (url_id) REFERENCES public.urlshorten(url_id) ON DELETE CASCADE;


---------------------------------------------------
---------------- DEFINE FUNCTION ------------------
---------------------------------------------------

--
-- Name: last_updated(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION last_updated() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.last_update = CURRENT_TIMESTAMP;
    RETURN NEW;
END $$;


ALTER FUNCTION public.last_updated() OWNER TO postgres;


---------------------------------------------------
--------------- DEFINE TRIGGER --------------------
---------------------------------------------------
--
-- Name: last_updated; Type: TRIGGER; Schema: public; Owner: postgres
--

-- Add last_updated column to all tables
CREATE TRIGGER last_updated BEFORE UPDATE ON urlShorten FOR EACH ROW EXECUTE PROCEDURE last_updated();


----------------------------------------------------
--------------- SAMPLE DATA ------------------------
----------------------------------------------------

INSERT INTO public.urlshorten(
	shorturl, originalurl, click, createdby, last_update)
	VALUES ( '1', 'https://globalnews.ca/news/6624856/bc-covid-update-tuesday', '7', 'Michael', '2020-03-03');

INSERT INTO public.urllog(
 url_id, shorturl, last_accessed)
VALUES ('1000', '1', '2020-03-03');