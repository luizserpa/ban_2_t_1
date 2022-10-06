--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)

-- Started on 2022-10-06 16:09:25 -03

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 213 (class 1259 OID 16480)
-- Name: deslocamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deslocamento (
    cod_des integer NOT NULL,
    cod_veiculo integer,
    cod_mot integer,
    descricao character varying(250) NOT NULL,
    status_des character varying(2) NOT NULL
);


ALTER TABLE public.deslocamento OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16515)
-- Name: incidente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.incidente (
    cod_des integer NOT NULL,
    cod_inc integer NOT NULL,
    tipo_inc character varying(2) NOT NULL,
    descricao character varying(250) NOT NULL
);


ALTER TABLE public.incidente OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16455)
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca (
    cod_marca integer NOT NULL,
    nome character varying(70) NOT NULL,
    data_desat integer
);


ALTER TABLE public.marca OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16460)
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modelo (
    cod_modelo integer NOT NULL,
    cod_marca integer,
    nome character varying(70) NOT NULL,
    combustivel character varying(1) NOT NULL,
    motor real NOT NULL,
    data_desat integer
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16450)
-- Name: motorista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motorista (
    cod_mot integer NOT NULL,
    nome character varying(70) NOT NULL,
    cnh character varying(70) NOT NULL,
    data_desat integer
);


ALTER TABLE public.motorista OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16495)
-- Name: passagem_deslocamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.passagem_deslocamento (
    cod_des integer NOT NULL,
    seq integer NOT NULL,
    tipo_des character varying(2) NOT NULL
);


ALTER TABLE public.passagem_deslocamento OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16470)
-- Name: veiculo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.veiculo (
    cod_veiculo integer NOT NULL,
    chassi character varying(100) NOT NULL,
    cod_modelo integer,
    placa character varying(7) NOT NULL,
    uf character varying(2) NOT NULL,
    data_desat integer
);


ALTER TABLE public.veiculo OWNER TO postgres;

--
-- TOC entry 3395 (class 0 OID 16480)
-- Dependencies: 213
-- Data for Name: deslocamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.deslocamento (cod_des, cod_veiculo, cod_mot, descricao, status_des) FROM stdin;
1	1	1	Foi buscar parafuso	EA
2	3	3	Visita cliente	FI
3	5	4	Foi buscar o Joao	FI
4	2	2	Visita cliente	EA
5	6	4	Foi no banco	FI
6	6	5	Foi buscar o chefe	FI
\.


--
-- TOC entry 3397 (class 0 OID 16515)
-- Dependencies: 215
-- Data for Name: incidente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.incidente (cod_des, cod_inc, tipo_inc, descricao) FROM stdin;
3	1	M	140 na pista de 60
5	2	F	Acendeu a luz do ABS
6	3	A	Bateu no portao da casa do chefe
\.


--
-- TOC entry 3392 (class 0 OID 16455)
-- Dependencies: 210
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.marca (cod_marca, nome, data_desat) FROM stdin;
1	Volkswagen	0
2	Chevrolet	0
\.


--
-- TOC entry 3393 (class 0 OID 16460)
-- Dependencies: 211
-- Data for Name: modelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.modelo (cod_modelo, cod_marca, nome, combustivel, motor, data_desat) FROM stdin;
1	1	Gol	F	1	0
2	1	Fox	F	1.6	0
3	2	Onix	G	1	0
\.


--
-- TOC entry 3391 (class 0 OID 16450)
-- Dependencies: 209
-- Data for Name: motorista; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.motorista (cod_mot, nome, cnh, data_desat) FROM stdin;
1	Carlos A.	12341	0
2	Carlos B.	4123	0
3	Carlos C.	123412	0
4	Joao A.	12441	0
5	Joao B.	12441	0
6	Joao C.	1244123	0
\.


--
-- TOC entry 3396 (class 0 OID 16495)
-- Dependencies: 214
-- Data for Name: passagem_deslocamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.passagem_deslocamento (cod_des, seq, tipo_des) FROM stdin;
1	1	FI
2	1	FI
2	2	FI
3	1	FI
3	2	FI
4	1	FI
5	1	FI
5	2	FI
6	1	FI
6	2	FI
\.


--
-- TOC entry 3394 (class 0 OID 16470)
-- Dependencies: 212
-- Data for Name: veiculo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.veiculo (cod_veiculo, chassi, cod_modelo, placa, uf, data_desat) FROM stdin;
1	1	1	AAAA000	SC	0
2	2	1	AAAA001	SC	0
3	3	1	AAAA002	SC	0
4	1	2	AAAA004	SC	0
5	2	2	AAAA005	SC	0
6	1	3	AAAA005	SC	0
\.


--
-- TOC entry 3241 (class 2606 OID 16484)
-- Name: deslocamento deslocamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deslocamento
    ADD CONSTRAINT deslocamento_pkey PRIMARY KEY (cod_des);


--
-- TOC entry 3245 (class 2606 OID 16519)
-- Name: incidente incidente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidente
    ADD CONSTRAINT incidente_pkey PRIMARY KEY (cod_des, cod_inc);


--
-- TOC entry 3235 (class 2606 OID 16459)
-- Name: marca marca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (cod_marca);


--
-- TOC entry 3237 (class 2606 OID 16464)
-- Name: modelo modelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (cod_modelo);


--
-- TOC entry 3233 (class 2606 OID 16454)
-- Name: motorista motorista_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorista
    ADD CONSTRAINT motorista_pkey PRIMARY KEY (cod_mot);


--
-- TOC entry 3243 (class 2606 OID 16499)
-- Name: passagem_deslocamento passagem_deslocamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.passagem_deslocamento
    ADD CONSTRAINT passagem_deslocamento_pkey PRIMARY KEY (cod_des, seq);


--
-- TOC entry 3239 (class 2606 OID 16474)
-- Name: veiculo veiculo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_pkey PRIMARY KEY (cod_veiculo);


--
-- TOC entry 3249 (class 2606 OID 16490)
-- Name: deslocamento deslocamento_cod_mot_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deslocamento
    ADD CONSTRAINT deslocamento_cod_mot_fkey FOREIGN KEY (cod_mot) REFERENCES public.motorista(cod_mot);


--
-- TOC entry 3248 (class 2606 OID 16485)
-- Name: deslocamento deslocamento_cod_veiculo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deslocamento
    ADD CONSTRAINT deslocamento_cod_veiculo_fkey FOREIGN KEY (cod_veiculo) REFERENCES public.veiculo(cod_veiculo);


--
-- TOC entry 3251 (class 2606 OID 16520)
-- Name: incidente incidente_cod_des_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidente
    ADD CONSTRAINT incidente_cod_des_fkey FOREIGN KEY (cod_des) REFERENCES public.deslocamento(cod_des);


--
-- TOC entry 3246 (class 2606 OID 16465)
-- Name: modelo modelo_cod_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_cod_marca_fkey FOREIGN KEY (cod_marca) REFERENCES public.marca(cod_marca);


--
-- TOC entry 3250 (class 2606 OID 16500)
-- Name: passagem_deslocamento passagem_deslocamento_cod_des_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.passagem_deslocamento
    ADD CONSTRAINT passagem_deslocamento_cod_des_fkey FOREIGN KEY (cod_des) REFERENCES public.deslocamento(cod_des);


--
-- TOC entry 3247 (class 2606 OID 16475)
-- Name: veiculo veiculo_cod_modelo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_cod_modelo_fkey FOREIGN KEY (cod_modelo) REFERENCES public.modelo(cod_modelo);


-- Completed on 2022-10-06 16:09:25 -03

--
-- PostgreSQL database dump complete
--

