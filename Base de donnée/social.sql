--
-- PostgreSQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.4

-- Started on 2020-12-22 14:46:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2966 (class 1262 OID 16438)
-- Name: social; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE social WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C.UTF-8' LC_CTYPE = 'C.UTF-8';


ALTER DATABASE social OWNER TO postgres;

\connect social

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 209 (class 1255 OID 16525)
-- Name: trigger_set_timestamp(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_set_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.trigger_set_timestamp() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 206 (class 1259 OID 16492)
-- Name: autorisation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autorisation (
    id integer NOT NULL,
    utilisateur_id integer,
    salon_id integer
);


ALTER TABLE public.autorisation OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16529)
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id integer NOT NULL,
    text text,
    moment timestamp with time zone DEFAULT now() NOT NULL,
    salon_id integer,
    utilisateur_id integer
);


ALTER TABLE public.message OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16527)
-- Name: message1_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message1_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message1_id_seq OWNER TO postgres;

--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 207
-- Name: message1_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.message1_id_seq OWNED BY public.message.id;


--
-- TOC entry 203 (class 1259 OID 16452)
-- Name: salon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salon (
    id integer NOT NULL,
    titre text
);


ALTER TABLE public.salon OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16450)
-- Name: salon_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.salon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.salon_id_seq OWNER TO postgres;

--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 202
-- Name: salon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.salon_id_seq OWNED BY public.salon.id;


--
-- TOC entry 205 (class 1259 OID 16480)
-- Name: utilisateurs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateurs (
    id integer NOT NULL,
    pseudo character varying(30)
);


ALTER TABLE public.utilisateurs OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16478)
-- Name: utilisateurs_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.utilisateurs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utilisateurs_id_seq OWNER TO postgres;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 204
-- Name: utilisateurs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.utilisateurs_id_seq OWNED BY public.utilisateurs.id;


--
-- TOC entry 2817 (class 2604 OID 16532)
-- Name: message id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message1_id_seq'::regclass);


--
-- TOC entry 2815 (class 2604 OID 16455)
-- Name: salon id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salon ALTER COLUMN id SET DEFAULT nextval('public.salon_id_seq'::regclass);


--
-- TOC entry 2816 (class 2604 OID 16483)
-- Name: utilisateurs id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateurs ALTER COLUMN id SET DEFAULT nextval('public.utilisateurs_id_seq'::regclass);


--
-- TOC entry 2958 (class 0 OID 16492)
-- Dependencies: 206
-- Data for Name: autorisation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autorisation VALUES (1, 1, 1);
INSERT INTO public.autorisation VALUES (2, 1, 2);
INSERT INTO public.autorisation VALUES (3, 2, 3);
INSERT INTO public.autorisation VALUES (4, 2, 2);
INSERT INTO public.autorisation VALUES (5, 2, 1);


--
-- TOC entry 2960 (class 0 OID 16529)
-- Dependencies: 208
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.message VALUES (1, 'aaaaaaaaa', '2020-12-22 18:36:05.707558+00', 1, 1);
INSERT INTO public.message VALUES (2, 'bbbbbbbbb', '2020-12-22 18:36:05.707558+00', 1, 2);
INSERT INTO public.message VALUES (3, 'oooooo', '2020-12-22 19:36:05.707558+00', 1, 2);
INSERT INTO public.message VALUES (4, 'fjadhfjakfjkadfjk', '2020-12-22 19:01:07.848208+00', 2, 2);
INSERT INTO public.message VALUES (5, NULL, '2020-12-22 19:35:05.318617+00', NULL, NULL);


--
-- TOC entry 2955 (class 0 OID 16452)
-- Dependencies: 203
-- Data for Name: salon; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.salon VALUES (1, 'ChatPrive');
INSERT INTO public.salon VALUES (2, 'ChatPublic');
INSERT INTO public.salon VALUES (3, 'Statistiques ');


--
-- TOC entry 2957 (class 0 OID 16480)
-- Dependencies: 205
-- Data for Name: utilisateurs; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateurs VALUES (1, 'User1');
INSERT INTO public.utilisateurs VALUES (2, 'User2');


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 207
-- Name: message1_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.message1_id_seq', 5, true);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 202
-- Name: salon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.salon_id_seq', 1, true);


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 204
-- Name: utilisateurs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilisateurs_id_seq', 1, false);


--
-- TOC entry 2824 (class 2606 OID 16496)
-- Name: autorisation autorisation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autorisation
    ADD CONSTRAINT autorisation_pkey PRIMARY KEY (id);


--
-- TOC entry 2826 (class 2606 OID 16538)
-- Name: message message1_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message1_pkey PRIMARY KEY (id);


--
-- TOC entry 2820 (class 2606 OID 16460)
-- Name: salon salon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salon
    ADD CONSTRAINT salon_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 2606 OID 16485)
-- Name: utilisateurs utilisateurs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateurs
    ADD CONSTRAINT utilisateurs_pkey PRIMARY KEY (id);


--
-- TOC entry 2827 (class 2620 OID 16539)
-- Name: message set_timestamp; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp BEFORE UPDATE ON public.message FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();


-- Completed on 2020-12-22 14:46:23

--
-- PostgreSQL database dump complete
--

