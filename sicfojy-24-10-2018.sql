--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.23
-- Dumped by pg_dump version 9.3.23
-- Started on 2018-10-24 23:50:01

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 253 (class 1255 OID 24845)
-- Name: fn_log_audit(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.fn_log_audit() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  IF (TG_OP = 'DELETE') THEN
    INSERT INTO public.bitacora("entidad", "sentencia", "usuario", "fecha","hora", "accion")
           VALUES (upper(TG_TABLE_NAME), OLD, user, now(),current_time,'ELIMINAR');
    RETURN OLD;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO public.bitacora ("entidad", "sentencia", "usuario", "fecha","hora", "accion")
           VALUES (upper(TG_TABLE_NAME), NEW, user, now(),current_time,'MODIFICAR');
    RETURN NEW;
  ELSIF (TG_OP = 'INSERT') THEN
    INSERT INTO public.bitacora ("entidad", "sentencia", "usuario", "fecha","hora", "accion")
           VALUES (upper(TG_TABLE_NAME), NEW, user, now(),current_time,'REGISTRAR');
    RETURN NEW;
  END IF;
  RETURN NULL;
END;
$$;


ALTER FUNCTION public.fn_log_audit() OWNER TO postgres;

--
-- TOC entry 252 (class 1255 OID 24846)
-- Name: spdetsel(character, character); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.spdetsel(codp character, opc character) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
DECLARE variable1 VARCHAR(4) default NULL;
DECLARE varbool BOOLEAN default false;
BEGIN
    variable1 = (SELECT id FROM public.opcxperfil WHERE codperfil = codp AND codopc = opc);
    IF (variable1 ISNULL) THEN
        varbool = false;
    ELSE
        varbool = true;
    END IF;
    RETURN varbool;
END; 
$$;


ALTER FUNCTION public.spdetsel(codp character, opc character) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 24847)
-- Name: bien; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.bien (
    serial character varying(20),
    id integer NOT NULL,
    medida character varying(10),
    cantidad character varying(10),
    ninventario character varying(19),
    id_tipob integer,
    nombreb character varying(40),
    id_marca integer,
    id_modelo integer,
    tipo_move integer,
    eliminacion character(5),
    id_move integer
);


ALTER TABLE public.bien OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 24850)
-- Name: bien_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bien_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bien_id_seq OWNER TO postgres;

--
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 172
-- Name: bien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bien_id_seq OWNED BY public.bien.id;


--
-- TOC entry 173 (class 1259 OID 24852)
-- Name: bitacora; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.bitacora (
    id integer NOT NULL,
    entidad character varying(30),
    sentencia character varying(200),
    usuario character varying(30),
    fecha date,
    formulario character varying(30),
    accion character varying(30),
    descripcion character varying(100),
    hora time without time zone,
    id_empleado integer
);


ALTER TABLE public.bitacora OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 24855)
-- Name: bitacora_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bitacora_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bitacora_id_seq OWNER TO postgres;

--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 174
-- Name: bitacora_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bitacora_id_seq OWNED BY public.bitacora.id;


--
-- TOC entry 175 (class 1259 OID 24857)
-- Name: cargo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.cargo (
    id integer NOT NULL,
    nombrecar character varying(20),
    descripcion character varying(30),
    codcar integer,
    eliminacion character(5)
);


ALTER TABLE public.cargo OWNER TO postgres;

--
-- TOC entry 2379 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN cargo.eliminacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cargo.eliminacion IS 'estado logico';


--
-- TOC entry 176 (class 1259 OID 24860)
-- Name: cargo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cargo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cargo_id_seq OWNER TO postgres;

--
-- TOC entry 2380 (class 0 OID 0)
-- Dependencies: 176
-- Name: cargo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cargo_id_seq OWNED BY public.cargo.id;


--
-- TOC entry 177 (class 1259 OID 24862)
-- Name: catedra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.catedra (
    id_catedra integer NOT NULL,
    nombrec character varying(30),
    descripcion character varying(50),
    eliminacion character(5)
);


ALTER TABLE public.catedra OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 24865)
-- Name: catedra_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.catedra_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.catedra_id_seq OWNER TO postgres;

--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 178
-- Name: catedra_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.catedra_id_seq OWNED BY public.catedra.id_catedra;


--
-- TOC entry 179 (class 1259 OID 24867)
-- Name: empleado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.empleado (
    id integer NOT NULL,
    id_usuario integer NOT NULL,
    id_cargo integer,
    id_persona integer,
    eliminacion character(5)
);


ALTER TABLE public.empleado OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 24870)
-- Name: empleado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empleado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empleado_id_seq OWNER TO postgres;

--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 180
-- Name: empleado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_seq OWNED BY public.empleado.id;


--
-- TOC entry 181 (class 1259 OID 24872)
-- Name: empleado_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empleado_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empleado_id_usuario_seq OWNER TO postgres;

--
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 181
-- Name: empleado_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_usuario_seq OWNED BY public.empleado.id_usuario;


--
-- TOC entry 182 (class 1259 OID 24874)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.estudiante (
    id_estudiante integer NOT NULL,
    id_representante integer,
    id_catedra integer,
    id_persona integer,
    eliminacion character(5)
);


ALTER TABLE public.estudiante OWNER TO postgres;

--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN estudiante.id_persona; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.estudiante.id_persona IS 'referenciaa a tabla personar';


--
-- TOC entry 183 (class 1259 OID 24877)
-- Name: estudiante_catedra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.estudiante_catedra (
    id_est integer NOT NULL,
    id_estudiante integer,
    id_catedra integer
);


ALTER TABLE public.estudiante_catedra OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 24880)
-- Name: estudiante_catedra_id_est_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estudiante_catedra_id_est_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estudiante_catedra_id_est_seq OWNER TO postgres;

--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 184
-- Name: estudiante_catedra_id_est_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estudiante_catedra_id_est_seq OWNED BY public.estudiante_catedra.id_est;


--
-- TOC entry 185 (class 1259 OID 24882)
-- Name: estudiante_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estudiante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estudiante_id_seq OWNER TO postgres;

--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 185
-- Name: estudiante_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estudiante_id_seq OWNED BY public.estudiante.id_estudiante;


--
-- TOC entry 186 (class 1259 OID 24884)
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.marca (
    id_marca integer NOT NULL,
    nombrema character varying(20),
    descripcion character varying(30),
    eliminacion character(5)
);


ALTER TABLE public.marca OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24887)
-- Name: marca_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.marca_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marca_id_seq OWNER TO postgres;

--
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 187
-- Name: marca_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marca_id_seq OWNED BY public.marca.id_marca;


--
-- TOC entry 188 (class 1259 OID 24889)
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.modelo (
    id_modelo integer NOT NULL,
    nombremo character varying(20),
    id_marca integer,
    eliminacion character(5)
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24892)
-- Name: modelo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.modelo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.modelo_id_seq OWNER TO postgres;

--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 189
-- Name: modelo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modelo_id_seq OWNED BY public.modelo.id_modelo;


--
-- TOC entry 190 (class 1259 OID 24894)
-- Name: modulo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.modulo (
    id_modulo integer NOT NULL,
    nombremd character varying(30),
    descripcionmd character varying(50),
    eliminacion character(5)
);


ALTER TABLE public.modulo OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 24897)
-- Name: modulo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.modulo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.modulo_id_seq OWNER TO postgres;

--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 191
-- Name: modulo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modulo_id_seq OWNED BY public.modulo.id_modulo;


--
-- TOC entry 192 (class 1259 OID 24899)
-- Name: movimiento_bien; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.movimiento_bien (
    id_mov_bien integer NOT NULL,
    fecha_move date,
    justificacion character varying(50),
    reponsable integer,
    id_bien integer,
    id_tipo_movimiento integer,
    id_tipo_siniestro integer,
    id_mv_modulo integer,
    id_mv_programa integer,
    cantidad character varying(2),
    id_mp_estudiante integer,
    id_mp_catedra integer,
    id_mp_tprestamo integer,
    fecha_d date,
    nombrebien character varying(30)
);


ALTER TABLE public.movimiento_bien OWNER TO postgres;

--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN movimiento_bien.id_bien; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento_bien.id_bien IS 'referencia de la tabla bien';


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN movimiento_bien.id_mv_modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento_bien.id_mv_modulo IS 'referencia de tabla modulo';


--
-- TOC entry 193 (class 1259 OID 24902)
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimiento_bien_id_mov_bien_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimiento_bien_id_mov_bien_seq OWNER TO postgres;

--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 193
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_bien_id_mov_bien_seq OWNED BY public.movimiento_bien.id_mov_bien;


--
-- TOC entry 232 (class 1259 OID 33545)
-- Name: movimiento_prestamo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.movimiento_prestamo (
    id_mv_prestamo integer NOT NULL,
    justificacion character varying(50),
    id_mp_responsable integer,
    id_mp_estudiante integer,
    id_mp_catedra integer,
    id_mp_tprestamo integer,
    fecha_i date,
    fecha_d date,
    cantidad character(2),
    id_mp_bien integer,
    id_mp_tipomv integer
);


ALTER TABLE public.movimiento_prestamo OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 33543)
-- Name: movimiento_prestamo_id_mv_prestamo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimiento_prestamo_id_mv_prestamo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimiento_prestamo_id_mv_prestamo_seq OWNER TO postgres;

--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 231
-- Name: movimiento_prestamo_id_mv_prestamo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_prestamo_id_mv_prestamo_seq OWNED BY public.movimiento_prestamo.id_mv_prestamo;


--
-- TOC entry 194 (class 1259 OID 24904)
-- Name: opcion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.opcion (
    id integer NOT NULL,
    codopc character varying(4) NOT NULL,
    nombre character varying(50)
);


ALTER TABLE public.opcion OWNER TO postgres;

--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 194
-- Name: TABLE opcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.opcion IS 'Opciones del Sistema';


--
-- TOC entry 195 (class 1259 OID 24907)
-- Name: opcion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.opcion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.opcion_id_seq OWNER TO postgres;

--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 195
-- Name: opcion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.opcion_id_seq OWNED BY public.opcion.id;


--
-- TOC entry 196 (class 1259 OID 24909)
-- Name: opcxperfil; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.opcxperfil (
    id integer NOT NULL,
    codopc character varying(4),
    codperfil character(4)
);


ALTER TABLE public.opcxperfil OWNER TO postgres;

--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE opcxperfil; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.opcxperfil IS 'Detalle de Opciones por Perfil del Sistema';


--
-- TOC entry 197 (class 1259 OID 24912)
-- Name: opcxperfil_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.opcxperfil_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.opcxperfil_id_seq OWNER TO postgres;

--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 197
-- Name: opcxperfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.opcxperfil_id_seq OWNED BY public.opcxperfil.id;


--
-- TOC entry 198 (class 1259 OID 24914)
-- Name: perfil; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.perfil (
    id_perfil integer NOT NULL,
    codperfil character varying(4) NOT NULL,
    descrip character varying(45) NOT NULL,
    estatus character(1) DEFAULT 'A'::bpchar,
    eliminacion character(5)
);


ALTER TABLE public.perfil OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24918)
-- Name: perfil_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.perfil_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.perfil_id_seq OWNER TO postgres;

--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 199
-- Name: perfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.perfil_id_seq OWNED BY public.perfil.id_perfil;


--
-- TOC entry 200 (class 1259 OID 24920)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.persona (
    id_persona integer NOT NULL,
    nombreper character varying(40),
    apellidoper character varying(40),
    cedulaper character varying(8),
    telefonoper character varying(12),
    generoper character varying(1),
    direccionper character varying(50),
    fnacimientoper date,
    tipoper integer,
    nacionper character varying(1),
    id_profesor integer,
    id_estudiante integer,
    id_per_representante integer,
    eliminacion character(5)
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24923)
-- Name: persona_id_persona_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.persona_id_persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.persona_id_persona_seq OWNER TO postgres;

--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 201
-- Name: persona_id_persona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persona_id_persona_seq OWNED BY public.persona.id_persona;


--
-- TOC entry 202 (class 1259 OID 24925)
-- Name: profesor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.profesor (
    id_profesor integer NOT NULL,
    id_personapro integer,
    eliminacion character(5)
);


ALTER TABLE public.profesor OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24928)
-- Name: profesor_catedra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.profesor_catedra (
    id_prof_cate integer NOT NULL,
    id_profesor integer,
    id_catedra integer
);


ALTER TABLE public.profesor_catedra OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 24931)
-- Name: profesor_catedra_id_prof_cate_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profesor_catedra_id_prof_cate_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profesor_catedra_id_prof_cate_seq OWNER TO postgres;

--
-- TOC entry 2403 (class 0 OID 0)
-- Dependencies: 204
-- Name: profesor_catedra_id_prof_cate_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_catedra_id_prof_cate_seq OWNED BY public.profesor_catedra.id_prof_cate;


--
-- TOC entry 205 (class 1259 OID 24933)
-- Name: profesor_estudiante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.profesor_estudiante (
    id_profestudiante integer NOT NULL,
    id_profesor integer,
    id_estudiante integer
);


ALTER TABLE public.profesor_estudiante OWNER TO postgres;

--
-- TOC entry 2404 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE profesor_estudiante; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.profesor_estudiante IS 'inner profesor estudiantes';


--
-- TOC entry 206 (class 1259 OID 24936)
-- Name: profesor_estudiante_id_profestudiante_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profesor_estudiante_id_profestudiante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profesor_estudiante_id_profestudiante_seq OWNER TO postgres;

--
-- TOC entry 2405 (class 0 OID 0)
-- Dependencies: 206
-- Name: profesor_estudiante_id_profestudiante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_estudiante_id_profestudiante_seq OWNED BY public.profesor_estudiante.id_profestudiante;


--
-- TOC entry 207 (class 1259 OID 24938)
-- Name: profesor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profesor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profesor_id_seq OWNER TO postgres;

--
-- TOC entry 2406 (class 0 OID 0)
-- Dependencies: 207
-- Name: profesor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_id_seq OWNED BY public.profesor.id_profesor;


--
-- TOC entry 208 (class 1259 OID 24940)
-- Name: programa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.programa (
    id_programa integer NOT NULL,
    nombrepro character varying(30),
    descripcionpro character varying(30),
    id_modulo integer,
    eliminacion character(5)
);


ALTER TABLE public.programa OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24943)
-- Name: programa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.programa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.programa_id_seq OWNER TO postgres;

--
-- TOC entry 2407 (class 0 OID 0)
-- Dependencies: 209
-- Name: programa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.programa_id_seq OWNED BY public.programa.id_programa;


--
-- TOC entry 210 (class 1259 OID 24945)
-- Name: representante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.representante (
    id_representante integer NOT NULL,
    id_persona integer,
    id_estudiante integer,
    eliminacion character(5)
);


ALTER TABLE public.representante OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24948)
-- Name: representante_id_representante_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.representante_id_representante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.representante_id_representante_seq OWNER TO postgres;

--
-- TOC entry 2408 (class 0 OID 0)
-- Dependencies: 211
-- Name: representante_id_representante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.representante_id_representante_seq OWNED BY public.representante.id_representante;


--
-- TOC entry 212 (class 1259 OID 24950)
-- Name: tipo_movimiento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tipo_movimiento (
    id_tipo_move integer NOT NULL,
    nombre_move character varying(30),
    eliminacion character(5)
);


ALTER TABLE public.tipo_movimiento OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 24953)
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_movimiento_id_tipo_move_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_movimiento_id_tipo_move_seq OWNER TO postgres;

--
-- TOC entry 2409 (class 0 OID 0)
-- Dependencies: 213
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_movimiento_id_tipo_move_seq OWNED BY public.tipo_movimiento.id_tipo_move;


--
-- TOC entry 234 (class 1259 OID 33551)
-- Name: tipo_prestamo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tipo_prestamo (
    id_tipo_prestamo integer NOT NULL,
    nombre_tprestamo character varying(30),
    eliminacion character varying(5),
    descripciontp character varying(50),
    dias character(2)
);


ALTER TABLE public.tipo_prestamo OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 33549)
-- Name: tipo_prestamo_id_tipo_prestamo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_prestamo_id_tipo_prestamo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_prestamo_id_tipo_prestamo_seq OWNER TO postgres;

--
-- TOC entry 2410 (class 0 OID 0)
-- Dependencies: 233
-- Name: tipo_prestamo_id_tipo_prestamo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_prestamo_id_tipo_prestamo_seq OWNED BY public.tipo_prestamo.id_tipo_prestamo;


--
-- TOC entry 214 (class 1259 OID 24955)
-- Name: tipo_siniestro; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tipo_siniestro (
    id_tipos integer NOT NULL,
    nombrets character varying(30),
    descripcionts character varying(30),
    eliminacion character(5)
);


ALTER TABLE public.tipo_siniestro OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24958)
-- Name: tipo_siniestro_id_tipos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_siniestro_id_tipos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_siniestro_id_tipos_seq OWNER TO postgres;

--
-- TOC entry 2411 (class 0 OID 0)
-- Dependencies: 215
-- Name: tipo_siniestro_id_tipos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_siniestro_id_tipos_seq OWNED BY public.tipo_siniestro.id_tipos;


--
-- TOC entry 216 (class 1259 OID 24960)
-- Name: tipobien; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tipobien (
    id_tipob integer NOT NULL,
    nombretb character varying(20),
    descripcion character varying(50),
    eliminacion character(5)
);


ALTER TABLE public.tipobien OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24963)
-- Name: tipobien_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipobien_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipobien_id_seq OWNER TO postgres;

--
-- TOC entry 2413 (class 0 OID 0)
-- Dependencies: 217
-- Name: tipobien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipobien_id_seq OWNED BY public.tipobien.id_tipob;


--
-- TOC entry 218 (class 1259 OID 24965)
-- Name: tipoinstrumento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tipoinstrumento (
    id_tipo_instrumento integer NOT NULL,
    nombreti character varying(30),
    descripcionti character varying(50)
);


ALTER TABLE public.tipoinstrumento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24968)
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipoinstrumento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipoinstrumento_id_seq OWNER TO postgres;

--
-- TOC entry 2414 (class 0 OID 0)
-- Dependencies: 219
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipoinstrumento_id_seq OWNED BY public.tipoinstrumento.id_tipo_instrumento;


--
-- TOC entry 220 (class 1259 OID 24970)
-- Name: tipopersona; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.tipopersona (
    id_tipopersona integer NOT NULL,
    nombretipoper character varying(20),
    eliminacion character(5)
);


ALTER TABLE public.tipopersona OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24973)
-- Name: tipopersona_id_tipopersona_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipopersona_id_tipopersona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipopersona_id_tipopersona_seq OWNER TO postgres;

--
-- TOC entry 2415 (class 0 OID 0)
-- Dependencies: 221
-- Name: tipopersona_id_tipopersona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipopersona_id_tipopersona_seq OWNED BY public.tipopersona.id_tipopersona;


--
-- TOC entry 222 (class 1259 OID 24975)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    clave text NOT NULL,
    id_perfil integer,
    login character varying(30),
    status character varying(5),
    id_persona integer,
    eliminacion character(5),
    id_usu_cargo integer
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24981)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2416 (class 0 OID 0)
-- Dependencies: 223
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 229 (class 1259 OID 33527)
-- Name: vbien; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vbien AS
 SELECT bn.id,
    bn.medida,
    bn.nombreb,
    bn.serial,
    bn.ninventario,
    mr.id_marca,
    md.id_modelo,
    md.nombremo,
    mr.nombrema,
    bn.cantidad,
    bn.eliminacion,
    bn.id_tipob,
    tb.nombretb
   FROM (((public.bien bn
     JOIN public.marca mr ON (((bn.id_marca)::text = (mr.id_marca)::text)))
     JOIN public.tipobien tb ON (((bn.id_tipob)::text = (tb.id_tipob)::text)))
     JOIN public.modelo md ON (((bn.id_modelo)::text = (md.id_modelo)::text)))
  WHERE (bn.tipo_move = 5);


ALTER TABLE public.vbien OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24988)
-- Name: vbien_movimiento_bien; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vbien_movimiento_bien AS
 SELECT bn.id,
    bn.medida,
    bn.nombreb,
    bn.serial,
    mr.id_marca,
    md.id_modelo,
    md.nombremo,
    mr.nombrema,
    bn.cantidad,
    bn.id_tipob,
    tb.nombretb,
    tmv.nombre_move,
    mv.fecha_move,
    mv.justificacion,
    rs.nombreper,
    ts.nombrets
   FROM ((((((((public.bien bn
     JOIN public.marca mr ON (((bn.id_marca)::text = (mr.id_marca)::text)))
     JOIN public.tipobien tb ON (((bn.id_tipob)::text = (tb.id_tipob)::text)))
     JOIN public.modelo md ON (((bn.id_modelo)::text = (md.id_modelo)::text)))
     JOIN public.movimiento_bien mv ON (((mv.id_bien)::text = (bn.id)::text)))
     JOIN public.tipo_movimiento tmv ON (((mv.id_tipo_movimiento)::text = (tmv.id_tipo_move)::text)))
     JOIN public.persona rs ON (((mv.reponsable)::text = (rs.id_persona)::text)))
     JOIN public.tipo_siniestro ts ON (((ts.id_tipos)::text = (mv.id_tipo_siniestro)::text)))
     JOIN public.programa pro ON (((mv.id_mv_programa)::text = (pro.id_programa)::text)));


ALTER TABLE public.vbien_movimiento_bien OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 41741)
-- Name: vcatedra_estudiante; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vcatedra_estudiante AS
 SELECT eca.id_est,
    per.nombreper,
    ca.id_catedra,
    per.nacionper,
    per.id_persona,
    per.apellidoper,
    per.cedulaper,
    per.telefonoper,
    per.generoper,
    per.direccionper,
    per.fnacimientoper,
    per.tipoper,
    ca.nombrec
   FROM (((public.estudiante_catedra eca
     JOIN public.estudiante est ON (((eca.id_estudiante)::text = (est.id_persona)::text)))
     JOIN public.catedra ca ON (((eca.id_catedra)::text = (ca.id_catedra)::text)))
     JOIN public.persona per ON (((per.id_persona)::text = (est.id_persona)::text)))
  WHERE (per.tipoper = 2);


ALTER TABLE public.vcatedra_estudiante OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 33522)
-- Name: vcatedra_profesor; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vcatedra_profesor AS
 SELECT pca.id_prof_cate,
    per.nombreper,
    per.tipoper,
    ca.id_catedra,
    ca.eliminacion,
    ca.nombrec
   FROM (((public.profesor_catedra pca
     JOIN public.profesor pro ON (((pca.id_profesor)::text = (pro.id_personapro)::text)))
     JOIN public.catedra ca ON (((pca.id_catedra)::text = (ca.id_catedra)::text)))
     JOIN public.persona per ON (((((per.id_profesor)::text = (pro.id_profesor)::text) AND (per.tipoper = 3)) AND (ca.eliminacion = 'TRUE'::bpchar))));


ALTER TABLE public.vcatedra_profesor OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 49945)
-- Name: vestudiante; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vestudiante AS
 SELECT pr.nombreper,
    pr.nacionper,
    pr.id_persona,
    pr.apellidoper,
    pr.cedulaper,
    pr.telefonoper,
    pr.generoper,
    pr.direccionper,
    pr.fnacimientoper,
    pr.tipoper,
    ca.nombrec,
    pr.eliminacion,
    es.id_representante
   FROM ((public.estudiante es
     JOIN public.persona pr ON (((es.id_persona)::text = (pr.id_persona)::text)))
     JOIN public.catedra ca ON (((es.id_catedra)::text = (ca.id_catedra)::text)));


ALTER TABLE public.vestudiante OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 41733)
-- Name: vinstrumentos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vinstrumentos AS
 SELECT bn.id,
    bn.medida,
    bn.nombreb,
    bn.serial,
    bn.ninventario,
    mr.id_marca,
    md.id_modelo,
    md.nombremo,
    mr.nombrema,
    bn.cantidad,
    bn.eliminacion,
    bn.id_tipob,
    tb.nombretb
   FROM (((public.bien bn
     JOIN public.marca mr ON (((bn.id_marca)::text = (mr.id_marca)::text)))
     JOIN public.tipobien tb ON (((bn.id_tipob)::text = (tb.id_tipob)::text)))
     JOIN public.modelo md ON (((bn.id_modelo)::text = (md.id_modelo)::text)))
  WHERE ((bn.tipo_move = 5) AND (bn.id_tipob = 7));


ALTER TABLE public.vinstrumentos OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 41709)
-- Name: vmoviento_asignar; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vmoviento_asignar AS
 SELECT tmv.nombre_move,
    mv.fecha_move,
    mv.justificacion,
    mv.cantidad,
    rs.nombreper,
    rs.id_persona,
    bn.nombreb,
    ts.nombrets,
    bn.id,
    md.nombremd,
    pro.nombrepro,
    pro.id_programa
   FROM ((((((public.movimiento_bien mv
     JOIN public.tipo_movimiento tmv ON (((mv.id_tipo_movimiento)::text = (tmv.id_tipo_move)::text)))
     JOIN public.persona rs ON (((mv.reponsable)::text = (rs.id_persona)::text)))
     JOIN public.bien bn ON (((mv.id_bien)::text = (bn.id)::text)))
     JOIN public.tipo_siniestro ts ON (((ts.id_tipos)::text = (mv.id_tipo_siniestro)::text)))
     JOIN public.modulo md ON (((mv.id_mv_modulo)::text = (md.id_modulo)::text)))
     JOIN public.programa pro ON (((mv.id_mv_programa)::text = (pro.id_programa)::text)));


ALTER TABLE public.vmoviento_asignar OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 49930)
-- Name: vmoviento_prestamo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vmoviento_prestamo AS
 SELECT tmv.nombre_move,
    mv.fecha_move,
    mv.fecha_d,
    mv.justificacion,
    mv.cantidad,
    rs.nombreper AS responsable,
    es.nombreper AS estudiante,
    bn.nombreb,
    bn.id AS bien,
    ca.nombrec,
    ca.id_catedra,
    tp.nombre_tprestamo AS tipo_prestamo,
    tp.dias,
    rs.id_persona
   FROM ((((((public.movimiento_bien mv
     JOIN public.tipo_movimiento tmv ON (((mv.id_tipo_movimiento)::text = (tmv.id_tipo_move)::text)))
     JOIN public.persona rs ON (((mv.reponsable)::text = (rs.id_persona)::text)))
     JOIN public.persona es ON (((mv.id_mp_estudiante)::text = (es.id_persona)::text)))
     JOIN public.catedra ca ON (((mv.id_mp_catedra)::text = (ca.id_catedra)::text)))
     JOIN public.tipo_prestamo tp ON (((mv.id_mp_tprestamo)::text = (tp.id_tipo_prestamo)::text)))
     JOIN public.bien bn ON (((mv.id_bien)::text = (bn.id)::text)))
  WHERE (mv.id_tipo_movimiento = 4);


ALTER TABLE public.vmoviento_prestamo OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25013)
-- Name: vmovientos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vmovientos AS
 SELECT tmv.nombre_move,
    mv.fecha_move,
    mv.justificacion,
    rs.nombreper,
    bn.nombreb,
    ts.nombrets,
    bn.id,
    rs.id_persona
   FROM ((((public.movimiento_bien mv
     JOIN public.tipo_movimiento tmv ON (((mv.id_tipo_movimiento)::text = (tmv.id_tipo_move)::text)))
     JOIN public.persona rs ON (((mv.reponsable)::text = (rs.id_persona)::text)))
     JOIN public.bien bn ON (((mv.id_bien)::text = (bn.id)::text)))
     JOIN public.tipo_siniestro ts ON (((ts.id_tipos)::text = (mv.id_tipo_siniestro)::text)));


ALTER TABLE public.vmovientos OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 33532)
-- Name: vprofesor; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vprofesor AS
 SELECT pr.id_persona,
    pr.nombreper,
    pr.apellidoper,
    pr.cedulaper,
    pr.telefonoper,
    pr.generoper,
    pr.direccionper,
    pr.fnacimientoper,
    pr.tipoper,
    pr.eliminacion,
    ca.nombrec
   FROM (((public.profesor pro
     JOIN public.persona pr ON (((pro.id_personapro)::text = (pr.id_persona)::text)))
     JOIN public.profesor_catedra pca ON (((pca.id_profesor)::text = (pro.id_personapro)::text)))
     JOIN public.catedra ca ON ((((pca.id_catedra)::text = (ca.id_catedra)::text) AND (pr.tipoper = 3))));


ALTER TABLE public.vprofesor OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25023)
-- Name: vrepresentante; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vrepresentante AS
 SELECT pr.nombreper,
    pr.apellidoper,
    pr.cedulaper,
    pr.telefonoper,
    pr.generoper,
    pr.direccionper,
    pr.fnacimientoper,
    pr.tipoper
   FROM ((public.representante re
     JOIN public.persona pr ON (((re.id_persona)::text = (pr.id_persona)::text)))
     JOIN public.estudiante es ON (((re.id_representante)::text = (es.id_representante)::text)));


ALTER TABLE public.vrepresentante OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 33517)
-- Name: vrusuporperfil; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vrusuporperfil AS
 SELECT us.id,
    per.cedulaper,
    per.nombreper,
    per.apellidoper,
    per.telefonoper,
    per.direccionper,
    per.generoper,
    ca.nombrecar,
    us.login,
    us.clave,
    us.eliminacion,
    per.fnacimientoper,
    us.id_perfil,
    per.nacionper
   FROM ((public.usuario us
     JOIN public.persona per ON (((us.id_persona)::text = (per.id_persona)::text)))
     JOIN public.cargo ca ON (((us.id_usu_cargo)::text = (ca.id)::text)));


ALTER TABLE public.vrusuporperfil OWNER TO postgres;

--
-- TOC entry 2038 (class 2604 OID 25032)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien ALTER COLUMN id SET DEFAULT nextval('public.bien_id_seq'::regclass);


--
-- TOC entry 2039 (class 2604 OID 25033)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bitacora ALTER COLUMN id SET DEFAULT nextval('public.bitacora_id_seq'::regclass);


--
-- TOC entry 2040 (class 2604 OID 25034)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo ALTER COLUMN id SET DEFAULT nextval('public.cargo_id_seq'::regclass);


--
-- TOC entry 2041 (class 2604 OID 25035)
-- Name: id_catedra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catedra ALTER COLUMN id_catedra SET DEFAULT nextval('public.catedra_id_seq'::regclass);


--
-- TOC entry 2042 (class 2604 OID 25036)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id SET DEFAULT nextval('public.empleado_id_seq'::regclass);


--
-- TOC entry 2043 (class 2604 OID 25037)
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id_usuario SET DEFAULT nextval('public.empleado_id_usuario_seq'::regclass);


--
-- TOC entry 2044 (class 2604 OID 25038)
-- Name: id_estudiante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante ALTER COLUMN id_estudiante SET DEFAULT nextval('public.estudiante_id_seq'::regclass);


--
-- TOC entry 2045 (class 2604 OID 25039)
-- Name: id_est; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra ALTER COLUMN id_est SET DEFAULT nextval('public.estudiante_catedra_id_est_seq'::regclass);


--
-- TOC entry 2046 (class 2604 OID 25040)
-- Name: id_marca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca ALTER COLUMN id_marca SET DEFAULT nextval('public.marca_id_seq'::regclass);


--
-- TOC entry 2047 (class 2604 OID 25041)
-- Name: id_modelo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo ALTER COLUMN id_modelo SET DEFAULT nextval('public.modelo_id_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 25042)
-- Name: id_modulo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo ALTER COLUMN id_modulo SET DEFAULT nextval('public.modulo_id_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 25043)
-- Name: id_mov_bien; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien ALTER COLUMN id_mov_bien SET DEFAULT nextval('public.movimiento_bien_id_mov_bien_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 33548)
-- Name: id_mv_prestamo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_prestamo ALTER COLUMN id_mv_prestamo SET DEFAULT nextval('public.movimiento_prestamo_id_mv_prestamo_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 25044)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion ALTER COLUMN id SET DEFAULT nextval('public.opcion_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 25045)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil ALTER COLUMN id SET DEFAULT nextval('public.opcxperfil_id_seq'::regclass);


--
-- TOC entry 2053 (class 2604 OID 25046)
-- Name: id_perfil; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil ALTER COLUMN id_perfil SET DEFAULT nextval('public.perfil_id_seq'::regclass);


--
-- TOC entry 2054 (class 2604 OID 25047)
-- Name: id_persona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona ALTER COLUMN id_persona SET DEFAULT nextval('public.persona_id_persona_seq'::regclass);


--
-- TOC entry 2055 (class 2604 OID 25048)
-- Name: id_profesor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor ALTER COLUMN id_profesor SET DEFAULT nextval('public.profesor_id_seq'::regclass);


--
-- TOC entry 2056 (class 2604 OID 25049)
-- Name: id_prof_cate; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra ALTER COLUMN id_prof_cate SET DEFAULT nextval('public.profesor_catedra_id_prof_cate_seq'::regclass);


--
-- TOC entry 2057 (class 2604 OID 25050)
-- Name: id_profestudiante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante ALTER COLUMN id_profestudiante SET DEFAULT nextval('public.profesor_estudiante_id_profestudiante_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 25051)
-- Name: id_programa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa ALTER COLUMN id_programa SET DEFAULT nextval('public.programa_id_seq'::regclass);


--
-- TOC entry 2059 (class 2604 OID 25052)
-- Name: id_representante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante ALTER COLUMN id_representante SET DEFAULT nextval('public.representante_id_representante_seq'::regclass);


--
-- TOC entry 2060 (class 2604 OID 25053)
-- Name: id_tipo_move; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento ALTER COLUMN id_tipo_move SET DEFAULT nextval('public.tipo_movimiento_id_tipo_move_seq'::regclass);


--
-- TOC entry 2067 (class 2604 OID 33554)
-- Name: id_tipo_prestamo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_prestamo ALTER COLUMN id_tipo_prestamo SET DEFAULT nextval('public.tipo_prestamo_id_tipo_prestamo_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 25054)
-- Name: id_tipos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_siniestro ALTER COLUMN id_tipos SET DEFAULT nextval('public.tipo_siniestro_id_tipos_seq'::regclass);


--
-- TOC entry 2062 (class 2604 OID 25055)
-- Name: id_tipob; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipobien ALTER COLUMN id_tipob SET DEFAULT nextval('public.tipobien_id_seq'::regclass);


--
-- TOC entry 2063 (class 2604 OID 25056)
-- Name: id_tipo_instrumento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipoinstrumento ALTER COLUMN id_tipo_instrumento SET DEFAULT nextval('public.tipoinstrumento_id_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 25057)
-- Name: id_tipopersona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona ALTER COLUMN id_tipopersona SET DEFAULT nextval('public.tipopersona_id_tipopersona_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 25058)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 2311 (class 0 OID 24847)
-- Dependencies: 171
-- Data for Name: bien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 47, '34', '1', '01922', 7, 'FORROS', 13, 7, 2, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 48, '34', '1', '01922', 7, 'FORROS', 13, 7, 2, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 49, '34', '1', '01922', 7, 'FORROS', 13, 7, 2, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 50, '34', '1', '01922', 7, 'FORROS', 13, 7, 2, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 51, '34', '1', '01922', 7, 'FORROS', 13, 7, 2, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('12121', 33, '65', '1', '0010', 4, 'BIEN', 6, 1, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('1', 29, '1', '1', '12', 7, 'CUATRO', 2, 2, 3, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('123', 25, '3132', '1', '0133', 7, 'VIOLIN', 2, 2, 2, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('23331', 32, '33', '1', '00013', 6, 'MESA', 10, 10, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('1233', 45, '23', '1', '333', 7, 'EJE', 9, 9, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('1233', 44, '23', '1', '333', 7, 'EJE', 9, 9, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('39933', 34, '50', '1', '00050', 7, 'ESTUCHE', 7, 8, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('22', 43, '33', '1', '323', 7, 'MARACAS', 9, 9, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('22', 31, '33', '1', '00001', 7, 'MARACAS', 6, 1, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('22', 41, '33', '1', '323', 7, 'MARACAS', 9, 9, 4, 'FALSE', 69);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('22', 42, '33', '1', '323', 7, 'MARACAS', 9, 9, 4, 'FALSE', 70);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 52, '34', '1', '01922', 7, 'FORROS', 13, 7, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 53, '34', '1', '01922', 7, 'FORROS', 13, 7, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 54, '34', '1', '01922', 7, 'FORROS', 13, 7, 5, 'TRUE ', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 55, '34', '1', '01922', 7, 'FORROS', 13, 7, 5, 'FALSE', NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move, eliminacion, id_move) VALUES ('03332', 46, '34', '1', '01922', 7, 'FORROS', 13, 7, 2, 'FALSE', NULL);


--
-- TOC entry 2417 (class 0 OID 0)
-- Dependencies: 172
-- Name: bien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bien_id_seq', 55, true);


--
-- TOC entry 2313 (class 0 OID 24852)
-- Dependencies: 173
-- Data for Name: bitacora; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (120, 'bien', '(null,Mobiliario,23,null,"","","","")', 'postgres', '2018-07-25', NULL, 'REGISTRADO', NULL, '01:45:53.279696', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (2, 'bien', 'INSERT', 'postgres', '2018-07-20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (3, 'bien', 'ELIMINAR', 'postgres', '2018-07-20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (4, 'cargo', 'ELIMINAR', 'postgres', '2018-07-20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (6, 'cargo', 'ELIMINAR', 'postgres', '2018-07-22', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (7, 'marca', '(5,MARCAE,DADADA)', 'postgres', '2018-07-22', NULL, 'ELIMINAR', NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (8, 'marca', '(6,MARCAE,DESCRIPCION)', 'postgres', '2018-07-22', NULL, 'REGISTRADO', NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (10, 'marca', '(5,MARCAE,DADADA)', 'postgres', '2018-07-22', NULL, 'ELIMINADO', NULL, NULL, NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (122, 'CARGO', '(5,CONTADOR,CONTADES,)', 'postgres', '2018-07-25', NULL, 'MODIFICADO', NULL, '20:48:48.498272', 1);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (124, 'CARGO', '(22,SECRETARIA,"REGISTRA ESTUDIANTE",)', 'postgres', '2018-07-25', NULL, 'REGISTRAR', NULL, '20:56:49.344589', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (126, 'CARGO', '(23,CARGONOM,EJEMPLO,)', 'postgres', '2018-07-25', NULL, 'REGISTRAR', NULL, '21:11:59.810183', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (130, 'CARGO', '(25,"OTRO CARGO",DES,)', 'postgres', '2018-07-25', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL CARGO OTRO CARGO DES', '22:46:17.352687', 1);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (132, 'CARGO', '(25,"OTRO CARGO",DESCRIPCIONN,)', 'postgres', '2018-07-25', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO OTRO CARGO DESCRIPCIONN', '23:44:18.06656', 1);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (134, 'EMPLEADO', '(1,CESAR,MOLINA,25646854,"LA PASTORA",04125083262,M,ADMINISTRADOR,1996-08-05,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'MODIFICAR', NULL, '01:22:11.008663', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (136, 'USUARIO', '(3,123,6,CARMEN,)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:26:17.870484', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (138, 'USUARIO', '(4,"",6,"",)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:27:12.75854', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (140, 'USUARIO', '(1,123,6,ADMIN,TRUE)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:30:47.796356', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (141, 'USUARIO', '(2,123,6,CARMEN,)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:30:51.341394', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (142, 'USUARIO', '(3,"",6,"",)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:30:57.205951', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (143, 'USUARIO', '(4,"",6,"",)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:30:59.898912', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (145, 'EMPLEADO', '(2,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:34:12.801559', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (147, 'USUARIO', '(3,123,6,LOGAN,)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:37:03.172453', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (148, 'EMPLEADO', '(4,CARMEN,TORRES,6844714,YARITAGUA,04125083262,F,SECRETARIA,1978-09-12,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:37:03.183911', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (151, 'USUARIO', '(2,123,6,LOGAN,)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:41:51.594196', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (152, 'USUARIO', '(3,123,6,LOGAN,)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:41:54.840466', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (155, 'EMPLEADO', '(2,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '02:19:51.852139', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (156, 'EMPLEADO', '(4,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '02:19:51.852139', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (157, 'USUARIO', '(1,123,6,CARMEN,TRUE)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '02:19:51.868659', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (158, 'USUARIO', '(4,123,6,ADMIN,FALSE)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '21:17:24.418415', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (160, 'CARGO', '(1,ADMINISTRADOR,"REGISTRA ESTUDIANTES",)', 'postgres', '2018-07-26', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL CARGO ADMINISTRADOR REGISTRA ESTUDIANTES', '21:38:49.812286', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (161, 'CARGO', '(23,CARGONOM,"SEGIO ES DESCRIPCION",)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL CARGO CARGONOM SEGIO ES DESCRIPCION', '10:29:45.228342', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (163, 'ESTUDIANTE', '(23,25646854,CESAR,YARITAGUA,04125083262,CATEDRA,M,MOLINA,1996-08-05,V)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', NULL, '13:57:11.453536', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (165, 'ESTUDIANTE', '(23,25646854,AUGUSTO,"YARITAGUA LA PASTORA",04125083262,CATEDRA,M,MOLINA,1996-08-05,V)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL USUARIO AUGUSTO 25646854', '14:21:44.943682', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (167, 'PERFIL', '(6,1,ADMINISTRADOR,s,1)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', NULL, '15:43:29.242455', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (169, 'PROFESOR', '(3,josefa,gomez,25646854,04125083262,adasd,F,"SIN CATEDRA",2018-07-13,4)', 'postgres', '2018-07-29', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL PROFESOR josefa 25646854', '15:17:50.574069', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (171, 'TIPOBIEN', '(3,TIPOB,DESC)', 'postgres', '2018-07-29', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL TIPO BIENTIPOB DESC', '20:27:19.497234', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (172, 'BIEN', '(modelo,"",25,3,3132,3,23,3,)', 'postgres', '2018-08-02', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN weq 3132', '10:20:26.890042', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (174, 'BIEN', '(modelo,"",25,3,3132,3,23,3,,3,)', 'postgres', '2018-08-08', NULL, 'MODIFICAR', NULL, '10:15:47.445659', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (176, 'BIEN', '(modelo,"",25,3,3132,3,23,3,,6,1)', 'postgres', '2018-08-08', NULL, 'MODIFICAR', NULL, '10:19:14.912525', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (178, 'BIEN', '(modelo,123,25,3,3132,3,23,3,violin,6,1)', 'postgres', '2018-08-08', NULL, 'MODIFICAR', NULL, '17:12:58.567575', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (179, 'CARGO', '(22,SECRETARIA,"REGISTRAR ESTUDIANTE",)', 'postgres', '2018-08-12', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL CARGO SECRETARIA REGISTRAR ESTUDIANTE', '10:11:05.295689', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (56, 'usuario', '(1,123,6,ADMIN,TRUE)', 'postgres', '2018-07-23', NULL, 'MODIFICADO', NULL, '00:19:18.165521', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (181, 'PROGRAMA', '(2,PROGRAMA,DES)', 'postgres', '2018-08-12', NULL, 'ELIMINAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO SE ME OLVIDO EN EL YOUTONG 6 violin ', '13:51:35.253738', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (182, 'PROGRAMA', '(3,PROGRAMA,DESCRIPCION)', 'postgres', '2018-09-15', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL PROGRAMAPROGRAMA DESCRIPCION', '08:33:24.354516', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (184, 'PROGRAMA', '(3,PROGRAMA,DESCRIPCIONES)', 'postgres', '2018-09-15', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINÓ EL PROGRAMA PROGRAMA DESCRIPCIONES', '09:17:28.972785', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (185, 'BIEN', '("",123,30,"",3132,3,"",3,MARACAS,,)', 'postgres', '2018-09-18', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN MARACAS 123', '18:55:09.306055', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (187, 'BIEN', '(123,25,3132,3,"",3,VIOLIN,6,1)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL BIEN VIOLIN 123', '19:30:26.143262', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (189, 'MODELO', '(1,modelo,6)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', NULL, '20:33:38.442339', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (191, 'PROFESOR', '(3,JOSEFA,URTADO,25646854,04125083262,adasd,F,CATEDRA,2018-07-13,4)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL PROFESOR JOSEFA 25646854', '20:51:43.984092', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (193, 'TIPOINSTRUMENTO', '(1,DESCRIPCION,NOMBRE)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL REPRESENTATE NOMBRE DESCRIPCION', '21:58:47.801532', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (195, 'REPRESENTANTE', '(1,1,)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', NULL, '23:58:13.494886', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (197, 'ESTUDIANTE', '(23,25646854,AUGUSTO,"YARITAGUA LA PASTORA",04125083262,CATEDRA,M,MOLINA,1996-08-05,V,1)', 'postgres', '2018-09-19', NULL, 'MODIFICAR', NULL, '00:12:17.698471', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (199, 'ESTUDIANTE', '(23,1,4,2)', 'postgres', '2018-09-20', NULL, 'MODIFICAR', NULL, '19:02:43.481553', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (200, 'PROFESOR', '(3,,,)', 'postgres', '2018-09-30', NULL, 'MODIFICAR', NULL, '20:01:13.010432', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (202, 'EMPLEADO', '(6,4,,2)', 'postgres', '2018-09-30', NULL, 'MODIFICAR', NULL, '20:19:36.931928', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (203, 'PROGRAMA', '(4,"PROGRAMA NOMBRE",DESCRIP,)', 'postgres', '2018-10-02', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL PROGRAMAPROGRAMA NOMBRE DESCRIP', '07:36:24.530207', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (204, 'TIPOBIEN', '(3,DESCRIPCION,TIPOB)', 'postgres', '2018-10-02', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL REPRESENTATE TIPOB DESCRIPCION', '19:00:43.245818', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (206, 'CARGO', '(26,EJE,DD,)', 'postgres', '2018-10-02', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL CARGO EJE DD', '23:26:31.996732', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (207, 'PROGRAMA', '(4,"PROGRAMA NOMBRE",DESCRIP,1)', 'postgres', '2018-10-04', NULL, 'MODIFICAR', NULL, '11:44:24.376003', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (121, 'cargo', '(21,SECRETARIA,DESCRIPCION,)', 'postgres', '2018-07-25', NULL, 'MODIFICADO', NULL, '03:55:04.433321', 1);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (123, 'cargo', '(5,CONTADOR,CONTADES,)', 'postgres', '2018-07-25', NULL, 'ELIMINAR', NULL, '20:53:40.962946', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (125, 'CARGO', '(21,SECRETARIA,DESCRIPCION,)', 'postgres', '2018-07-25', NULL, 'ELIMINAR', NULL, '20:57:32.472385', 1);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (127, 'CARGO', '(24,JARDINERO,"LIMPIA LAS AREAS VERD",)', 'postgres', '2018-07-25', NULL, 'REGISTRAR', NULL, '21:13:28.598138', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (129, 'CARGO', '(24,JARDINERO,"LIMPIA LAS AREAS VERD",)', 'postgres', '2018-07-25', NULL, 'ELIMINAR', NULL, '22:45:54.000737', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (131, 'CARGO', '(25,"OTRO CARGO",DESCRIPCIONN,)', 'postgres', '2018-07-25', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICAO EL CARGO OTRO CARGO DESCRIPCIONN', '23:41:29.171708', 1);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (133, 'USUARIO', '(2,123,6,CARMEN,)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:20:33.598083', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (135, 'USUARIO', '(3,123,6,CARMEN,)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:23:14.203987', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (137, 'USUARIO', '(3,"",6,"",)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:26:36.608489', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (139, 'EMPLEADO', '(1,CESAR,MOLINA,25646854,"LA PASTORA",04125083262,M,ADMINISTRADOR,1996-08-05,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'ELIMINAR', NULL, '01:29:17.274657', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (144, 'USUARIO', '(1,123,6,CARMEN,FALSE)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:32:43.609869', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (146, 'USUARIO', '(2,123,6,LOGAN,)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '01:35:37.728923', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (149, 'EMPLEADO', '(2,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'MODIFICAR', NULL, '01:38:01.999644', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (150, 'EMPLEADO', '(4,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'MODIFICAR', NULL, '01:38:01.999644', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (153, 'EMPLEADO', '(2,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'MODIFICAR', NULL, '01:42:36.733937', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (154, 'EMPLEADO', '(4,CESAR,MOLINA,25646854,YARITAGUA,04125083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",1)', 'postgres', '2018-07-26', NULL, 'MODIFICAR', NULL, '01:42:36.733937', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (159, 'EMPLEADO', '(6,CESAR,MOLINA,11279233,YARITAGUA,04145083262,M,ADMINISTRADOR,1996-05-08,,,"V         ",4)', 'postgres', '2018-07-26', NULL, 'REGISTRAR', NULL, '21:18:59.093036', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (162, 'ESTUDIANTE', '(23,25646854,CESA,YARITAGUA,04125083262,CATEDRA,M,MOLINA,1996-08-05,V)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', NULL, '13:24:55.496904', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (164, 'ESTUDIANTE', '(23,25646854,AUGUSTO,YARITAGUA,04125083262,CATEDRA,M,MOLINA,1996-08-05,V)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', NULL, '14:07:10.530128', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (166, 'PROFESOR', '(3,josefa,gomez,25646854,04125083262,adasd,M,CATEDRA,2018-07-13,4)', 'postgres', '2018-07-28', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL PROFESOR josefa 25646854', '15:05:10.092709', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (168, 'CATEDRA', '(4,CATEDRA,DESCRICION)', 'postgres', '2018-07-29', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO CATEDRA CATEDRA DESCRICION', '14:20:59.753049', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (170, 'PROFESOR', '(3,josefa,gomez,25646854,04125083262,adasd,F,CATEDRA,2018-07-13,4)', 'postgres', '2018-07-29', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL PROFESOR josefa 25646854', '15:42:10.559132', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (173, 'BIEN', '(6,1,29,1,1,2,12,3,cuatro)', 'postgres', '2018-08-02', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN cuatro 1', '13:36:50.631797', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (175, 'BIEN', '(modelo,"",25,3,3132,3,23,3,,3,1)', 'postgres', '2018-08-08', NULL, 'MODIFICAR', NULL, '10:17:14.935541', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (177, 'BIEN', '(modelo,123,25,3,3132,3,23,3,,6,1)', 'postgres', '2018-08-08', NULL, 'MODIFICAR', NULL, '10:41:41.22565', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (180, 'PROGRAMA', '(2,PROGRAMA,DES)', 'postgres', '2018-08-12', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL PROGRAMAPROGRAMA DES', '13:51:25.544486', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (183, 'PROGRAMA', '(3,PROGRAMA,DESCRIPCIONES)', 'postgres', '2018-09-15', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL PROGRAMAPROGRAMA DESCRIPCIONES', '09:14:25.65998', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (186, 'BIEN', '("",123,30,"",3132,3,"",3,MARACAS,,)', 'postgres', '2018-09-18', NULL, 'ELIMINAR', NULL, '18:58:06.864287', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (188, 'BIEN', '(123,25,3132,3,"",3,VIOLIN,2,1)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL BIEN VIOLIN 123', '19:35:33.999407', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (190, 'BIEN', '(123,25,3132,3,"",3,VIOLIN,2,2)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL BIEN VIOLIN 123', '20:36:37.551016', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (192, 'TIPOINSTRUMENTO', '(1,DESCRIPCION,NOMBRE)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL REPRESENTATE NOMBRE DESCRIPCION', '21:55:29.001526', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (194, 'REPRESENTANTE', '(1,1,1)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', NULL, '23:56:07.717039', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (196, 'REPRESENTANTE', '(1,1,23)', 'postgres', '2018-09-18', NULL, 'MODIFICAR', NULL, '23:58:38.263082', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (198, 'ESTUDIANTE', '(23,1,,2)', 'postgres', '2018-09-19', NULL, 'MODIFICAR', NULL, '00:23:48.755364', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (201, 'PROFESOR', '(3,,2)', 'postgres', '2018-09-30', NULL, 'MODIFICAR', NULL, '20:19:19.859969', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (205, 'TIPOINSTRUMENTO', '(1,NOMBRE,DESCRIPCION)', 'postgres', '2018-10-02', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL REPRESENTATE NOMBRE DESCRIPCION', '19:11:29.910147', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (208, 'PROGRAMA', '(5,"PROGRA ESPACIAL","DESDE MI CASA",)', 'postgres', '2018-10-04', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL PROGRAMAPROGRA ESPACIAL DESDE MI CASA', '11:51:29.62235', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (209, 'PROGRAMA', '(5,"PROGRA ESPACIAL","DESDE MI CASA",1)', 'postgres', '2018-10-04', NULL, 'MODIFICAR', NULL, '12:23:47.09834', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (210, 'CARGO', '(27,CARGOUNO,DESCRIPUNO,)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '19:07:37.625288', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (211, 'CARGO', '(28,CARGODOS,DESCRIPDOS,)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '19:08:38.061317', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (212, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '19:09:06.121178', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (213, 'CARGO', '(30,DOCENTE,"PREPARA A LOS ESTUDIANTES",)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '19:09:52.882135', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (214, 'CARGO', '(31,EJEPLOA,EJEPLO,)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '19:10:34.012092', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (215, 'CARGO', '(32,EJEPLOA,EJEPLO,)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '19:10:43.573451', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (216, 'CARGO', '(32,EJEPLOA,EJEPLO,)', 'postgres', '2018-10-05', NULL, 'ELIMINAR', NULL, '19:12:39.982583', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (217, 'TIPOBIEN', '(3,TIPOB,DESCRIPCION)', 'postgres', '2018-10-05', NULL, 'MODIFICAR', NULL, '20:00:01.002171', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (218, 'TIPOBIEN', '(3,CUERDAS,"INSTRUMENTO DE HILOS")', 'postgres', '2018-10-05', NULL, 'MODIFICAR', NULL, '20:00:18.006563', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (219, 'TIPOBIEN', '(4,MADERA,DESPCAD)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:01:34.533873', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (220, 'TIPOBIEN', '(5,"DE AIRE",DEASDASDASASDA)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:01:48.694836', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (221, 'TIPOINSTRUMENTO', '(3,CUERDA,ASDADASDASD)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:04:28.486725', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (222, 'TIPOINSTRUMENTO', '(4,"DE AIRE",SADASDASDDASD)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:04:34.142604', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (223, 'TIPOINSTRUMENTO', '(5,"DE MADERA",QWEQWEQWE)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:04:41.869805', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (224, 'TIPOBIEN', '(6,MUEBLES,"TODO LO QUE INCLUYA M")', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:05:41.174305', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (225, 'TIPOBIEN', '(7,INSTRUMENTALES,"TODO SOBRE INSTRUMENT")', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:06:00.475575', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (226, 'BIEN', '(12313,31,333,4,00001,7,MARACAS,6,1)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '20:13:14.08038', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (227, 'MODULO', '(2,DDDDD,MODULO)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '23:00:19.050166', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (228, 'MODULO', '(3,DEEE,OTRO)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '23:00:24.917723', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (229, 'MODULO', '(4,EQEQEQWE,ASDADADASD)', 'postgres', '2018-10-05', NULL, 'REGISTRAR', NULL, '23:00:34.981096', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (230, 'PERFIL', '(3,6,ADMINISTRADOR,A)', 'postgres', '2018-10-06', NULL, 'REGISTRAR', NULL, '20:30:11.291341', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (231, 'PERFIL', '(3,6,ADMINISTRADOR,A)', 'postgres', '2018-10-06', NULL, 'ELIMINAR', NULL, '20:35:34.030016', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (232, 'PROGRAMA', '(6,DDDD,AAAA,2)', 'postgres', '2018-10-07', NULL, 'REGISTRAR', NULL, '19:01:31.187809', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (233, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1)', 'postgres', '2018-10-07', NULL, 'REGISTRAR', NULL, '19:20:44.261243', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (234, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,5)', 'postgres', '2018-10-07', NULL, 'MODIFICAR', NULL, '19:25:57.824464', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (235, 'CARGO', '(34,SSSS,QQQQQ,)', 'postgres', '2018-10-07', NULL, 'REGISTRAR', NULL, '21:38:41.363106', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (236, 'CARGO', '(26,EJE,DDDDD,)', 'postgres', '2018-10-07', NULL, 'MODIFICAR', NULL, '22:47:21.723233', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (237, 'CARGO', '(26,EJEMPLO,DDDDD,)', 'postgres', '2018-10-07', NULL, 'MODIFICAR', NULL, '22:49:09.473267', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (238, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,3)', 'postgres', '2018-10-08', NULL, 'MODIFICAR', NULL, '15:51:41.767452', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (239, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,5)', 'postgres', '2018-10-08', NULL, 'MODIFICAR', NULL, '15:55:53.065701', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (240, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,3)', 'postgres', '2018-10-08', NULL, 'MODIFICAR', NULL, '15:56:25.448209', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (241, 'BIEN', '(12121,33,65,5,0010,4,BIEN,6,1,5)', 'postgres', '2018-10-08', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR4 JUSTIFACCION ASIGNAR 2 BIEN ', '19:04:48.701742', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (242, 'BIEN', '(12121,33,65,5,0010,4,BIEN,6,1,2)', 'postgres', '2018-10-08', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR1 JUSTIFICADO BIEN 2 BIEN ', '20:12:31.989931', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (243, 'MARCA', '(7,"SIN MARCA",ARTESANALES)', 'postgres', '2018-10-09', NULL, 'REGISTRAR', NULL, '19:08:22.931724', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (244, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA")', 'postgres', '2018-10-09', NULL, 'REGISTRAR', NULL, '19:21:57.029495', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (245, 'MODELO', '(3,ALTERNATIVO,8)', 'postgres', '2018-10-09', NULL, 'REGISTRAR', NULL, '19:23:35.093329', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (246, 'BIEN', '(39933,34,50,50,00050,7,ESTUCHE,8,3,5)', 'postgres', '2018-10-09', NULL, 'REGISTRAR', NULL, '19:24:07.021292', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (247, 'BIEN', '(39933,34,50,50,00050,7,ESTUCHE,8,3,2)', 'postgres', '2018-10-09', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR APUESTO  2 ESTUCHE ', '20:07:59.371505', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (248, 'PROGRAMA', '(4,"PROGRAMA NOMBRE",DESCRIP,1)', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '16:15:58.737', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (249, 'CATEDRA', '(6,VIOLIN,VIOLIN)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (250, 'CATEDRA', '(7,VIOLA,VIOLA)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (251, 'CATEDRA', '(8,VIOLONCELLO,VIOLONCELLO)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (252, 'CATEDRA', '(9,CONTRABAJO,CONTRABAJO)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (253, 'CATEDRA', '(10,FLAUTA,FLAUTA)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (254, 'CATEDRA', '(11,OBOE,OBOE)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (255, 'CATEDRA', '(12,TUBA,TUBA)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (256, 'CATEDRA', '(13,"HISTORIA DE LA MUSICA","HISTORIA DE LA MUSICA")', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (257, 'CATEDRA', '(14,"LENGUAJE MUSICAL","LENGUAJE MUSICAL")', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (258, 'CATEDRA', '(15,TROMPETA,TROMPETA)', 'postgres', '2018-10-11', NULL, 'REGISTRAR', NULL, '16:41:13.562', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (259, 'PROFESOR', '(3,,4)', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:04:17.592', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (260, 'CARGO', '(1,ADMINISTRADOR,"REGISTRA ESTUDIANTES",,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:09.663', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (261, 'CARGO', '(22,SECRETARIA,"REGISTRAR ESTUDIANTE",,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:11.241', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (262, 'CARGO', '(23,CARGONOM,"SEGIO ES DESCRIPCION",,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:15.382', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (263, 'CARGO', '(26,EJEMPLO,DDDDD,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:18.147', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (264, 'CARGO', '(27,CARGOUNO,DESCRIPUNO,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:20.726', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (265, 'CARGO', '(28,CARGODOS,DESCRIPDOS,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:23.069', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (266, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:25.788', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (267, 'CARGO', '(30,DOCENTE,"PREPARA A LOS ESTUDIANTES",,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:28.773', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (268, 'CARGO', '(31,EJEPLOA,EJEPLO,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:31.257', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (269, 'CARGO', '(34,SSSS,QQQQQ,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'MODIFICAR', NULL, '17:46:34.82', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (270, 'CARGO', '(34,SSSS,QQQQQ,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO SSSS QQQQQ', '17:51:51.154', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (271, 'CARGO', '(31,EJEPLOA,EJEPLO,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO EJEPLOA EJEPLO', '17:53:23.219', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (272, 'CARGO', '(27,CARGOUNO,DESCRIPUNO,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO CARGOUNO DESCRIPUNO', '17:53:33.719', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (273, 'CARGO', '(28,CARGODOS,DESCRIPDOS,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO CARGODOS DESCRIPDOS', '17:53:41.625', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (274, 'CARGO', '(23,CARGONOM,"SEGIO ES DESCRIPCION",,"TRUE ")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO CARGONOM SEGIO ES DESCRIPCION', '17:53:48.266', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (275, 'CARGO', '(26,EJEMPLO,DDDDD,,"TRUE ")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO EJEMPLO DDDDD', '17:53:54.657', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (276, 'CARGO', '(35,EEEE,EEEE,,"true ")', 'postgres', '2018-10-12', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL CARGO EEEE EEEE', '07:33:33.284', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (277, 'CARGO', '(35,EEEE,EEEE,,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '07:36:17.412', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (278, 'CARGO', '(35,EEEE,EEEE,,"TRUE ")', 'postgres', '2018-10-12', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO EEEE EEEE', '07:38:04.601', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (279, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '08:45:16.789', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (280, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '08:45:16.852', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (281, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:36:17.026', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (282, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:36:17.151', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (283, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:37:23.465', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (284, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:37:23.512', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (285, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:37:46.074', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (286, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:37:46.106', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (287, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:37:55.575', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (288, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:37:55.621', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (289, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:44:07.674', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (488, 'BIEN', '(123,25,3132,0,"",7,VIOLIN,2,2,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICAR MOVIMIENTO ASIGNAR 25 JUSTIFICACIONES 4 VIOLIN ', '12:44:27.4', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (492, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,3,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO 4 JUSTIFICACION 4 VIOLIN ', '14:12:27.726', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (497, 'BIEN', '(000101,35,23232,1,0001001,4,MESASSSSS,2,2,5,FALSE)', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '16:19:11.962', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (498, 'BIEN', '(000101,36,23232,2,0001001,4,MESASSSSS,2,2,5,FALSE)', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '16:19:16.318', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (499, 'BIEN', '(000101,37,23232,3,0001001,4,MESASSSSS,2,2,5,FALSE)', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '16:19:20.509', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (507, 'MARCA', '(19,YAMAHA,YAMAHA,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:24:44.672', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (290, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:44:07.737', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (291, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:44:24.753', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (292, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:44:24.784', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (293, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:45:35.098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (294, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:45:35.191', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (295, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:47:56.662', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (296, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '09:47:56.709', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (297, 'CARGO', '(36,"CARGO EJEM",EJEMPLO,,"TRUE ")', 'postgres', '2018-10-12', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL CARGO CARGO EJEM EJEMPLO', '11:49:45.985', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (298, 'CARGO', '(36,"CARGO EJEM",EJEMPLO,,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '11:49:53.813', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (299, 'CARGO', '(36,"CARGO EJEM",EJEMPLO,,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '11:49:53.86', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (300, 'CARGO', '(36,"CARGO EJEM",EJEMPLO,,FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '11:49:57.235', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (301, 'CARGO', '(36,"CARGO EJEM",EJEMPLO,,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '11:49:57.282', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (302, 'CARGO', '(36,"CARGO EJEM",EJEMPLO,,"TRUE ")', 'postgres', '2018-10-12', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO EL CARGO CARGO EJEM EJEMPLO', '11:50:09.188', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (303, 'MARCA', '(6,MARCAE,DESCRIPCION,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:06:54.12', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (304, 'MARCA', '(7,"SIN MARCA",ARTESANALES,"TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:06:56.745', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (305, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:06:59.214', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (306, 'MARCA', '(2,NIKE,"ZAPATOS NIKE","TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:07:08.089', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (307, 'MARCA', '(6,MARCAE,DESCRIPCION,"TRUE ")', 'postgres', '2018-10-12', NULL, 'ELIMINAR', NULL, '20:07:22.23', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (308, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:07:37.574', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (309, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:07:37.652', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (310, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:41:01.499', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (311, 'MARCA', '(2,NIKE,"ZAPATOS NIKE","TRUE ")', 'postgres', '2018-10-12', NULL, 'MODIFICAR', NULL, '20:41:01.577', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (312, 'PROGRAMA', '(6,DDDD,AAAA,2)', 'postgres', '2018-10-12', NULL, 'ELIMINAR', NULL, '21:14:43.614', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (313, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:24:43.032', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (314, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:24:44.235', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (315, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:26:00.018', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (316, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:26:00.064', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (317, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:28:40.754', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (318, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:28:40.864', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (319, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:28:45.379', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (320, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '10:28:45.426', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (321, 'BIEN', '(123,25,3132,3,"",7,VIOLIN,2,2,)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '12:15:42.384', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (322, 'BIEN', '(1,29,1,2,12,7,cuatro,,,)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO EL ESTUDIANTE  ANGEL 15624789', '12:15:47.806', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (323, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '17:49:54.377', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (324, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '17:49:54.564', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (325, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:18.998', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (326, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:19.061', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (327, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:28.201', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (328, 'MARCA', '(2,NIKE,"ZAPATOS NIKE","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:28.404', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (329, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:31.717', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (330, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:31.764', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (331, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:35.905', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (332, 'MARCA', '(2,NIKE,"ZAPATOS NIKE","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:02:35.951', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (333, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:11:35.882', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (334, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:11:35.96', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (335, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:11:46.367', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (336, 'MARCA', '(2,NIKE,"ZAPATOS NIKE","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:11:46.413', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (337, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:12:06.164', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (338, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:12:06.242', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (339, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:14:33.025', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (340, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:14:33.135', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (341, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:14:37.651', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (342, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:14:37.76', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (343, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA",FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:14:42.166', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (344, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '18:14:42.26', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (345, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA","TRUE ")', 'postgres', '2018-10-14', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINO LA MARCA ALTERNATIVO 8', '18:14:58.213', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (346, 'BIEN', '(Mobiliario,23,"","","",,,,,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:45:55.457', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (347, 'BIEN', '(123,25,3132,3,"",7,VIOLIN,2,2,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:45:58.598', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (348, 'BIEN', '(1,29,1,2,12,7,cuatro,,,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:00.895', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (349, 'BIEN', '(12313,31,333,4,00001,7,MARACAS,6,1,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:03.145', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (350, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,3,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:05.442', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (351, 'BIEN', '(12121,33,65,5,0010,4,BIEN,6,1,2,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:08.176', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (352, 'BIEN', '(39933,34,50,50,00050,7,ESTUCHE,8,3,2,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:11.301', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (489, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR VIOLIN JOSE', '13:36:37.032', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (500, 'MODELO', '(4,ROSEWOOD,3,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:26.696', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (501, 'MODELO', '(5,"VIBE 60",4,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:32.422', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (353, 'MODULO', '(1,DESC,MODULO,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:31.036', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (354, 'MODULO', '(2,DDDDD,MODULO,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:33.801', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (355, 'MODULO', '(3,DEEE,OTRO,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:36.676', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (356, 'MODULO', '(4,EQEQEQWE,ASDADADASD,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '19:46:38.63', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (357, 'MODELO', '(1,modelo,6,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:08:53.634', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (358, 'MODELO', '(2,nombreM,2,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:08:57.962', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (359, 'MODELO', '(3,ALTERNATIVO,8,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:09:00.837', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (360, 'TIPOBIEN', '(3,CUERDAS,"INSTRUMENTO DE HILOS","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:10:57.089', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (361, 'TIPOBIEN', '(4,MADERA,DESPCAD,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:10:59.527', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (362, 'TIPOBIEN', '(5,"DE AIRE",DEASDASDASASDA,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:11:01.198', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (363, 'TIPOBIEN', '(6,MUEBLES,"TODO LO QUE INCLUYA M","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:11:03.324', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (364, 'TIPOBIEN', '(7,INSTRUMENTALES,"TODO SOBRE INSTRUMENT","TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:11:05.886', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (365, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:54:36.692', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (366, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:54:36.755', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (367, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:54:59.771', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (368, 'CARGO', '(29,DIRECTOR,"ADMINISTRA EL PLANTEL",,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '20:54:59.864', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (369, 'PERSONA', '(1,CESAR,MOLINA,25646854,04125083262,M,YARITAGUA,1996-08-05,5,V,,23,,"true ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:36:54.749', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (370, 'PERSONA', '(1,CESAR,MOLINA,25646854,04125083262,M,YARITAGUA,1996-08-05,5,V,,23,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:36:57.624', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (371, 'PERSONA', '(2,AUGUSTO,MOLINA,6844714,04125083262,M,"YARITAGUA LA PASTORA",1996-08-05,2,V,,,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:37:01.171', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (372, 'PERSONA', '(3,GOCHO,TORRES,6601992,04125083262,M,YARITAGUA,1996-08-05,4,V,3,,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:37:04.702', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (373, 'PERSONA', '(4,JOSE,GOMEZ,6601992,04265028463,M,"SAN JOSE",1996-06-04,3,V,3,,1,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:37:07.061', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (374, 'PERSONA', '(5,ANGEL,PEREZ,15624789,04125789451,M,"URB TERMO YARACUY",2002-07-11,2,V,,,1,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:37:10.749', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (375, 'PERSONA', '(1,CESAR,MOLINA,25646854,04125083262,M,YARITAGUA,1996-08-05,5,V,,23,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:39:52.813', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (376, 'PERSONA', '(2,AUGUSTO,MOLINA,6844714,04125083262,M,"YARITAGUA LA PASTORA",1996-08-05,2,V,,,,FALSE)', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:45:33.177', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (377, 'PERSONA', '(2,AUGUSTO,MOLINA,6844714,04125083262,M,"YARITAGUA LA PASTORA",1996-08-05,2,V,,,,"TRUE ")', 'postgres', '2018-10-14', NULL, 'MODIFICAR', NULL, '23:45:33.239', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (378, 'PERSONA', '(7,EEEEEE,EEEEEE,222222,222222,M,EEEEEEEEEEE,2018-10-03,,,,,,"TRUE ")', 'postgres', '2018-10-16', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL REPRESENTANTE EEEEEE 222222', '06:20:24.968', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (379, 'PERSONA', '(7,EEEEEE,EEEEEE,222222,222222,M,EEEEEEEEEEE,2018-10-03,5,,,,,"TRUE ")', 'postgres', '2018-10-16', NULL, 'MODIFICAR', NULL, '06:23:08.111', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (380, 'REPRESENTANTE', '(2,7,,"TRUE ")', 'postgres', '2018-10-16', NULL, 'REGISTRAR', NULL, '06:51:01.068', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (381, 'PERSONA', '(14,"HECTOR JULIO","PEREZ SALAS",29789658,04125269785,M,"LA TERMO YARACUY",2001-07-03,2,V,,,7,)', 'postgres', '2018-10-16', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL ESTUDIANTE  HECTOR JULIO 29789658', '06:53:45.773', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (382, 'PERSONA', '(14,"HECTOR JULIO","PEREZ SALAS",29789658,04125269785,M,"LA TERMO YARACUY",2001-07-03,2,V,,,7,"TRUE ")', 'postgres', '2018-10-16', NULL, 'MODIFICAR', NULL, '06:55:02.977', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (383, 'ESTUDIANTE', '(24,,15,14,"TRUE ")', 'postgres', '2018-10-16', NULL, 'REGISTRAR', NULL, '07:03:16.03', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (384, 'ESTUDIANTE', '(23,1,4,2,"TRUE ")', 'postgres', '2018-10-16', NULL, 'MODIFICAR', NULL, '07:03:20.186', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (385, 'ESTUDIANTE', '(24,7,15,14,"TRUE ")', 'postgres', '2018-10-16', NULL, 'MODIFICAR', NULL, '07:05:08.672', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (386, 'CATEDRA', '(16,"CLASES DE VIOLIN",VIOLIN,)', 'postgres', '2018-10-16', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO CATEDRA VIOLIN CLASES DE VIOLIN', '07:06:31.845', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (387, 'CATEDRA', '(16,"CLASES DE VIOLIN",VIOLIN,)', 'postgres', '2018-10-16', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINAR CATEDRA CLASES DE VIOLIN VIOLIN', '07:10:12.676', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (388, 'CATEDRA', '(17,VIOLA,VIOLA,FALSE)', 'postgres', '2018-10-16', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO CATEDRA VIOLA VIOLA', '07:11:02.801', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (389, 'CATEDRA', '(7,VIOLA,VIOLA,)', 'postgres', '2018-10-16', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMINAR CATEDRA VIOLA VIOLA', '07:18:53.479', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (390, 'MARCA', '(2,NIKE,"ZAPATOS NIKE",FALSE)', 'postgres', '2018-10-16', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINO LA MARCA NIKE 2', '10:33:25.212', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (391, 'MARCA', '(2,NIKE,"ZAPATOS NIKE","TRUE ")', 'postgres', '2018-10-16', NULL, 'MODIFICAR', NULL, '10:35:31.714', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (392, 'CATEDRA', '(4,CATEDRA,DESCRICION,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:03.861', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (393, 'CATEDRA', '(6,VIOLIN,VIOLIN,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:07.049', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (394, 'CATEDRA', '(8,VIOLONCELLO,VIOLONCELLO,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:10.908', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (395, 'CATEDRA', '(9,CONTRABAJO,CONTRABAJO,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:14.721', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (396, 'CATEDRA', '(10,FLAUTA,FLAUTA,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:18.94', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (397, 'CATEDRA', '(11,OBOE,OBOE,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:22.565', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (398, 'CATEDRA', '(12,TUBA,TUBA,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:25.674', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (399, 'CATEDRA', '(13,"HISTORIA DE LA MUSICA","HISTORIA DE LA MUSICA","TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:28.909', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (400, 'CATEDRA', '(14,"LENGUAJE MUSICAL","LENGUAJE MUSICAL","TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:32.268', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (401, 'CATEDRA', '(15,TROMPETA,TROMPETA,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:36.69', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (402, 'CATEDRA', '(17,VIOLA,VIOLA,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:39.596', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (403, 'PROFESOR', '(3,4,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:40:58.097', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (404, 'PROGRAMA', '(5,"PROGRA ESPACIAL","DESDE MI CASA",1,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '10:48:56.104', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (405, 'BIEN', '(12313,31,333,4,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '11:26:23.184', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (490, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR ASDASD 4 CUATRO 5 ', '14:07:35.799', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (493, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO DESINCORPORAR VIOLIN JOSE', '14:53:40.957', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (502, 'MODELO', '(6,"STUDIO 5C",5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:37.587', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (503, 'MODELO', '(7,BEGGINERS,1,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:42.856', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (504, 'MODELO', '(8,MV012R,7,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:46.805', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (505, 'MODELO', '(9,PRO2,9,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:50.544', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (406, 'BIEN', '(1,29,1,2,12,7,cuatro,,,5,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '11:29:01.761', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (407, 'BIEN', '(123,25,3132,3,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '11:29:02.8', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (408, 'BIEN', '(Mobiliario,23,"","","",,,,,5,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '11:29:05.507', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (409, 'BIEN', '(1,29,1,2,12,7,cuatro,2,2,5,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '11:29:58.614', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (410, 'BIEN', '(12313,31,333,4,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:24:10.666', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (411, 'MARCA', '(9,YAMAHA,YAMAHA,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (412, 'MARCA', '(10,IBANEZ,IBANEZ,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (413, 'MARCA', '(11,FENDER,FENDER,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (414, 'MARCA', '(12,SQUIER,SQUIER,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (415, 'MARCA', '(13,SCHECTER,SCHECTER,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (416, 'MARCA', '(14,NOVRE,NOVRE,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (417, 'MARCA', '(15,STENTOR,STENTOR,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (418, 'MARCA', '(16,LARRC,LARC,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (419, 'MARCA', '(17,BERBERIEN,BERBERIEN,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (420, 'MARCA', '(18,ASTOR,ASTOR,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (421, 'MARCA', '(19,YAMAHA,YAMAHA,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (422, 'MARCA', '(20,IBANEZ,IBANEZ,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (423, 'MARCA', '(21,FENDER,FENDER,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (424, 'MARCA', '(22,SQUIER,SQUIER,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (425, 'MARCA', '(23,SCHECTER,SCHECTER,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (426, 'MARCA', '(24,NOVRE,NOVRE,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (427, 'MARCA', '(25,STENTOR,STENTOR,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (428, 'MARCA', '(26,LARRC,LARC,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (429, 'MARCA', '(27,BERBERIEN,BERBERIEN,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (430, 'MARCA', '(28,ASTOR,ASTOR,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:22.027', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (431, 'MODELO', '(4,ROSEWOOD,3,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (432, 'MODELO', '(5,"VIBE 60",4,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (433, 'MODELO', '(6,"STUDIO 5C",5,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (434, 'MODELO', '(7,BEGGINERS,1,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (435, 'MODELO', '(8,MV012R,7,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (436, 'MODELO', '(9,PRO2,9,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (437, 'MODELO', '(10,"STUDENT I",10,)', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:26:59.653', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (438, 'MARCA', '(9,YAMAHA,YAMAHA,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:42.669', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (439, 'MARCA', '(10,IBANEZ,IBANEZ,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:45.591', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (440, 'MARCA', '(11,FENDER,FENDER,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:47.982', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (441, 'MARCA', '(12,SQUIER,SQUIER,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:49.951', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (442, 'MARCA', '(13,SCHECTER,SCHECTER,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:51.685', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (443, 'MARCA', '(14,NOVRE,NOVRE,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:53.56', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (444, 'MARCA', '(15,STENTOR,STENTOR,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:55.295', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (445, 'MARCA', '(16,LARRC,LARC,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:57.185', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (446, 'MARCA', '(17,BERBERIEN,BERBERIEN,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:27:59.107', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (447, 'MARCA', '(18,ASTOR,ASTOR,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:00.81', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (448, 'MARCA', '(18,ASTOR,ASTOR,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:02.592', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (449, 'MARCA', '(19,YAMAHA,YAMAHA,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:04.201', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (450, 'MARCA', '(20,IBANEZ,IBANEZ,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:21.107', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (451, 'MARCA', '(21,FENDER,FENDER,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:22.811', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (452, 'MARCA', '(22,SQUIER,SQUIER,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:24.201', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (453, 'MARCA', '(23,SCHECTER,SCHECTER,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:25.654', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (454, 'MARCA', '(24,NOVRE,NOVRE,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:27.264', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (455, 'MARCA', '(25,STENTOR,STENTOR,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:28.498', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (456, 'MARCA', '(26,LARRC,LARC,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:29.858', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (457, 'MARCA', '(27,BERBERIEN,BERBERIEN,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:31.389', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (458, 'MARCA', '(28,ASTOR,ASTOR,"TRUE ")', 'postgres', '2018-10-17', NULL, 'MODIFICAR', NULL, '12:28:32.701', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (459, 'MODULO', '(5,"ORQUESTA PRE-INFANTIL M.R.C","PRE-INFANTIL MANUEL RODRIGUEZ CARDENAS","TRUE ")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:31:03.532', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (460, 'MODULO', '(6,"CONSERVATORIO B.E.M","CONSERVATORIO BLANCA ESTRELLA DE MESCOLI","TRUE ")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:31:03.532', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (461, 'MODULO', '(7,"LAS PIEDRAS","MODULO LAS PIEDRAS","TRUE ")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:31:03.532', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (462, 'TIPOINSTRUMENTO', '(6,CUERDA,"INSTRUMENTOS DE CUERDAS")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:54:10.447', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (463, 'TIPOINSTRUMENTO', '(7,VIENTO,"INSTRUMENTOS DE VIENTO")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:54:10.447', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (464, 'TIPOINSTRUMENTO', '(8,PERCUSION,"INSTRUMENTOS DE PERCUSION")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:54:10.447', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (465, 'TIPOINSTRUMENTO', '(9,ELECTRICOS,"INSTRUMENTOS ELECTRICOS")', 'postgres', '2018-10-17', NULL, 'REGISTRAR', NULL, '12:54:10.447', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (466, 'BIEN', '(123,25,3132,3,"",7,VIOLIN,2,2,2,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR PARA LOS NIÑOS 4 VIOLIN ', '10:28:12.11', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (467, 'BIEN', '(1,29,1,2,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '14:20:59.101', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (471, 'BIEN', '(39933,34,50,8,00050,7,ESTUCHE,8,3,2,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR DFSFSF 4 CUATRO ', '19:23:25.76', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (468, 'PERFIL', '(6,1,ADMINISTRADOR,s,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR EEEEEE 4 CUATRO ', '16:42:25.495', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (469, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:23:12.307', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (470, 'BIEN', '(1,29,1,2,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:23:15.979', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (472, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,2,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:26:36.124', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (473, 'BIEN', '(123,25,3132,3,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:29:01.439', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (474, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:29:04.048', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (494, 'BIEN', '(000101,35,23232,1,0001001,4,MESASSSSS,2,2,5,FALSE)', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '16:14:03.683', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (475, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,3,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:29:12.377', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (476, 'BIEN', '(23331,32,33,4,00013,6,MESA,6,1,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:29:14.783', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (477, 'BIEN', '(12121,33,65,5,0010,4,BIEN,6,1,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:29:17.986', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (478, 'BIEN', '(39933,34,50,8,00050,7,ESTUCHE,8,3,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:29:20.845', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (479, 'BIEN', '(1,29,1,5,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', NULL, '19:35:14.399', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (480, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,2,"TRUE ")', 'postgres', '2018-10-18', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR JUSTIFIACIN 4 CUATRO ', '19:35:45.321', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (481, 'PERSONA', '(15,"CESAR AUGUSTO","MOLINA TORRES",25646854,04125083262,M,"LA PASTORA",1996-08-05,,V,,,,"TRUE ")', 'postgres', '2018-10-18', NULL, 'REGISTRAR', NULL, '20:11:36.033', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (482, 'PERSONA', '(16,"CESAR AUGUSTO","MOLINA TORRES",25646854,04125083262,M,"LA PASTORA",1996-08-05,,V,,,,"TRUE ")', 'postgres', '2018-10-18', NULL, 'REGISTRAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR CUATRO JOSE', '20:11:36.892', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (483, 'BIEN', '(1,29,1,5,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '06:49:33.482', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (484, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR JUSTIFICA 4 CUATRO ', '06:50:14.623', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (485, 'BIEN', '(1,29,1,5,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR CUATRO JOSE', '06:50:32.061', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (486, 'BIEN', '(1,29,1,0,12,7,CUATRO,2,2,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR JUSTIFICA 4 CUATRO ', '06:59:39.584', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (487, 'BIEN', '(1,29,1,5,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR CUATRO JOSE', '11:54:38.662', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (491, 'BIEN', '(1,29,1,5,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR CUATRO JOSE', '14:07:56.643', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (495, 'BIEN', '(000101,36,23232,2,0001001,4,MESASSSSS,2,2,5,FALSE)', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '16:14:03.773', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (496, 'BIEN', '(000101,37,23232,3,0001001,4,MESASSSSS,2,2,5,FALSE)', 'postgres', '2018-10-19', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN MESASSSSS 000101', '16:14:03.773', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (506, 'MODELO', '(10,"STUDENT I",10,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:21:54.79', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (508, 'MARCA', '(20,IBANEZ,IBANEZ,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:24:48.883', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (509, 'MARCA', '(21,FENDER,FENDER,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:24:53.727', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (510, 'MARCA', '(22,SQUIER,SQUIER,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:24:57.934', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (511, 'MARCA', '(23,SCHECTER,SCHECTER,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:25:01.727', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (512, 'MARCA', '(24,NOVRE,NOVRE,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:25:07.207', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (513, 'MARCA', '(25,STENTOR,STENTOR,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:25:11.36', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (514, 'MARCA', '(27,BERBERIEN,BERBERIEN,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:25:15.965', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (515, 'MARCA', '(28,ASTOR,ASTOR,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:25:20.235', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (516, 'MARCA', '(26,LARRC,LARC,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:26:03.775', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (517, 'BIEN', '(233,38,233,1,32,7,MARACAS,9,9,5,FALSE)', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '19:28:26.062', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (518, 'BIEN', '(233,39,233,1,32,7,MARACAS,9,9,5,FALSE)', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '19:28:26.097', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (519, 'BIEN', '(233,40,233,1,32,7,MARACAS,9,9,5,FALSE)', 'postgres', '2018-10-19', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN MARACAS 233', '19:28:26.097', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (520, 'BIEN', '(233,38,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:29:14.442', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (521, 'BIEN', '(233,40,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:29:20.418', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (522, 'BIEN', '(233,39,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:29:22.375', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (523, 'BIEN', '(233,39,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINO EL BIEN MARACAS 233', '19:39:07.201', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (524, 'BIEN', '(233,39,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINO EL BIEN MARACAS 233', '19:39:15.678', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (525, 'BIEN', '(233,39,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINO EL BIEN MARACAS 233', '19:39:23.069', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (526, 'BIEN', '(233,38,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:41:01.278', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (527, 'BIEN', '(233,39,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:41:04.508', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (528, 'BIEN', '(233,40,233,1,32,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'ELIMINAR', NULL, '19:41:09.094', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (529, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '19:42:26.431', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (530, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '19:42:26.443', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (531, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN MARACAS 22', '19:42:26.443', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (532, 'BIEN', '(12313,31,333,1,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '19:46:21.959', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (533, 'PROGRAMA', '(7,"PROGRAMA INTERNACIONAL",DESCRIPCION,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '20:12:30.746', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (534, 'BIEN', '(22,43,33,2,323,7,MARACAS,9,9,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR JUSTIFIACCIOMM 4 MARACAS 2 ', '20:42:21.896', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (535, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR SADAS 4 MARACAS 2 ', '20:43:34.507', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (536, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.638', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (537, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.638', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (538, 'BIEN', '(12313,31,333,1,00001,7,MARACAS,6,1,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.638', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (539, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.638', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (540, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.712', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (541, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.712', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (542, 'BIEN', '(12313,31,333,1,00001,7,MARACAS,6,1,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:04:48.712', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (543, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR DDDD 4 MARACAS 2 ', '21:04:48.712', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (544, 'BIEN', '(12313,31,333,1,00001,7,MARACAS,6,1,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:15:04.066', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (545, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:15:07.269', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (546, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:15:09.628', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (547, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,2,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:15:12.187', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (548, 'BIEN', '(22,42,33,2,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR MARACAS JOSE', '21:15:37.008', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (569, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '02:19:25.983', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (570, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:22:45.565', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (549, 'BIEN', '(22,43,33,2,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR ASDASDASDSADA 4 MARACAS 2 ', '21:15:39.676', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (550, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:25:57.534', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (551, 'BIEN', '(12313,31,333,1,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:26:04.729', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (571, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:22:50.002', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (552, 'BIEN', '(22,43,33,2,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR ADASDADADAD 4 MARACAS 2 ', '21:26:22.835', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (553, 'BIEN', '(22,43,33,2,323,7,MARACAS,9,9,2,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:29:47.365', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (554, 'BIEN', '(22,43,33,2,323,7,MARACAS,9,9,5,FALSE)', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR MARACAS JOSE', '21:30:33.245', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (555, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'REGISTRAR', NULL, '21:39:00.867', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (556, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN EJE 1233', '21:39:00.874', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (557, 'BIEN', '(1,29,1,1,12,7,CUATRO,2,2,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '21:45:40.572', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (558, 'BIEN', '(12313,31,33,1,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', NULL, '23:57:14.737', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (559, 'BIEN', '(22,31,33,1,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-19', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR POR QUE SI 4 EJE 2 ', '23:57:48.034', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (560, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICAR MOVIMIENTO ASIGNAR 45 POR QUE SI 4 EJE ', '01:37:35.49', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (561, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '01:53:10.568', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (572, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:22:54.08', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (562, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '01:53:49.021', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (563, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:03:01.061', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (573, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:23:01.987', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (574, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:23:28.175', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (575, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR DSADASDASD 4 EJE 2 ', '02:23:28.237', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (564, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR SDADS 4 EJE 2 ', '02:04:50.329', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (565, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '02:16:59.762', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (566, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:18:46.389', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (567, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:19:09.717', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (568, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:19:25.889', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (592, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR ASDADS 4 EJE 2 ', '02:42:55.396', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (576, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:29:35.665', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (577, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:29:39.321', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (578, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRU  ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:29:41.352', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (579, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:29:46.212', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (580, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:30:19.634', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (581, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR EEE2E 4 EJE 2 ', '02:30:19.697', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (582, 'BIEN', '(1233,45,23,2,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '02:30:43.025', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (583, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:37:24.782', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (584, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:37:27.938', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (585, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:37:31.11', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (586, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:37:59.47', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (587, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '02:37:59.564', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (588, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:42:15.74', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (589, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:42:18.99', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (590, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:42:20.552', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (591, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:42:55.318', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (593, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:43:22.709', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (594, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '02:43:22.772', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (679, 'BIEN', '(22,31,33,1,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '08:37:11.277', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (595, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR ASDASD 4 EJE 1 ', '02:43:57.772', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (596, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '02:44:08.382', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (597, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:48:27.167', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (598, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR ASDASDAD 4 EJE 2 ', '02:48:27.245', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (599, 'BIEN', '(1233,44,23,2,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICAR MOVIMIENTO ASIGNAR 44 ASGARD 4 EJE ', '02:48:45.308', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (600, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '02:49:25.387', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (601, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '03:00:24.148', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (602, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '03:00:26.445', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (603, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '03:00:34.07', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (604, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '03:00:35.601', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (605, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '03:01:35.493', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (606, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR SADASD 4 EJE 2 ', '03:01:35.571', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (607, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO 2 ASDADASDASDASD 4 VIOLIN ', '03:25:20.031', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (608, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO DESINCORPORAR VIOLIN JOSE', '03:28:07.644', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (609, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '03:28:41.535', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (610, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO SIGNAR EJE JOSE', '03:28:41.628', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (611, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO 2 GGGGGGGGGG 4 VIOLIN ', '03:38:14.294', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (612, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO DESINCORPORAR VIOLIN JOSE', '10:07:53.198', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (613, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO 2 ADADAD 4 VIOLIN ', '10:55:31.868', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (614, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO DESINCORPORAR VIOLIN JOSE', '10:57:05.729', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (629, 'BIEN', '(1,29,1,1,12,7,CUATRO,2,2,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO 2 ASDASDASDASD 4 CUATRO ', '19:29:15.341', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (615, 'BIEN', '(123,25,3132,2,"",7,VIOLIN,2,2,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN MODIFICO EL MOVIMIENTO REPRAR VIOLIN ', '10:57:21.26', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (616, 'BIEN', '(23331,32,33,1,00013,6,MESA,6,1,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '13:19:07.873', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (617, 'BIEN', '(123,25,3132,1,"",7,VIOLIN,2,2,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '13:19:09.451', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (618, 'BIEN', '(12121,33,65,1,0010,4,BIEN,6,1,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '13:19:11.482', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (619, 'BIEN', '(39933,34,50,1,00050,7,ESTUCHE,8,3,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '13:19:12.56', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (620, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '13:19:16.17', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (621, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,5,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '13:19:19.654', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (622, 'BIEN', '(123,25,3132,1,"",7,VIOLIN,2,2,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO DESINCORPORAR VIOLIN JOSE', '13:19:52.702', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (623, 'BIEN', '(Mobiliario,23,"","","",,,,,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'ELIMINAR', NULL, '14:02:38.513', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (624, 'ESTUDIANTE', '(23,1,,2,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '17:32:12.662', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (625, 'ESTUDIANTE', '(24,7,,14,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '17:32:15.084', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (626, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '19:14:29.047', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (627, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '19:20:13.224', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (628, 'BIEN', '(123,25,3132,1,"",7,VIOLIN,2,2,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR DASDASDASDAS 4 VIOLIN 1 ', '19:28:59.653', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (630, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,3,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '19:29:50.185', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (631, 'BIEN', '(123,25,3132,1,0133,7,VIOLIN,2,2,2,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '20:13:32.073', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (632, 'BIEN', '(23331,32,33,1,00013,6,MESA,10,1,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '20:15:48.403', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (633, 'BIEN', '(23331,32,33,1,00013,6,MESA,10,10,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '20:16:06.435', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (634, 'BIEN', '(39933,34,50,1,00050,7,ESTUCHE,7,8,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '20:16:55.983', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (635, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,4,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:12:01.982', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (636, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,4,FALSE)', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:12:15.045', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (637, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,3,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:12:53.764', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (638, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,3,"5    ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:12:58.576', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (639, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"5    ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:13:03.967', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (640, 'BIEN', '(1233,45,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:13:08.514', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (641, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', NULL, '22:14:14.812', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (642, 'BIEN', '(1233,44,23,1,333,7,EJE,9,9,5,"TRUE ")', 'postgres', '2018-10-20', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 44 EJE 4', '23:17:50.344', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (675, 'BIEN', '(39933,34,50,1,00050,7,ESTUCHE,7,8,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 41 MARACAS 4', '07:53:09.409', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (676, 'BIEN', '(22,31,33,1,00001,7,MARACAS,6,1,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO PRESTAMO ADASDASDASD 4 MARACAS 1 ', '08:08:12.516', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (677, 'BIEN', '(39933,34,50,1,00050,7,ESTUCHE,7,8,5,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 41 MARACAS 4', '08:35:12.228', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (678, 'BIEN', '(22,31,33,1,00001,7,MARACAS,6,1,5,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '08:37:07.714', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (680, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 41 MARACAS 4', '08:38:01.277', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (681, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '08:40:10.858', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (697, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,4,FALSE,70)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO PRESTAMO ADASDASD 4 MARACAS 2 ', '10:12:40.053', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (682, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 42 MARACAS 4', '08:40:24.545', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (698, 'MODELO', '(7,BEGGINERS,13,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:09:07.651', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (683, 'BIEN', '(22,43,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 43 MARACAS 4', '08:50:20.242', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (699, 'BIEN', '(03332,46,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:02.996', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (684, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 43 MARACAS 4', '08:52:45.916', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (700, 'BIEN', '(03332,47,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.074', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (685, 'BIEN', '(22,31,33,1,00001,7,MARACAS,6,1,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 43 MARACAS 4', '08:56:31.982', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (686, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,5,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '09:01:28.44', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (701, 'BIEN', '(03332,48,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.074', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (687, 'BIEN', '(22,31,33,1,00001,7,MARACAS,6,1,5,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 41 MARACAS 4', '09:01:32.908', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (702, 'BIEN', '(03332,49,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.074', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (688, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,4,FALSE)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 41 MARACAS 4', '09:06:45.538', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (703, 'BIEN', '(03332,50,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.074', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (704, 'BIEN', '(03332,51,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.074', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (705, 'BIEN', '(03332,52,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.09', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (706, 'BIEN', '(03332,53,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.09', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (707, 'BIEN', '(03332,54,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', NULL, '17:10:03.105', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (708, 'BIEN', '(03332,55,34,1,01922,7,FORROS,13,7,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'REGISTRAR', 'EL USUARIO ADMIN REGISTRO EL BIEN FORROS 03332', '17:10:03.121', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (709, 'MODELO', '(4,ROSEWOOD,9,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:12:57.937', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (710, 'MODELO', '(5,"VIBE 60",10,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:11.718', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (689, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,5,"TRUE ",)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 42 MARACAS 4', '09:14:59.077', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (711, 'MODELO', '(5,"VIBE 60",10,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:13.531', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (690, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,4,FALSE,67)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 42 MARACAS 4', '09:35:41.705', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (691, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,4,"TRUE ",67)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '09:47:50.17', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (692, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,4,"TRUE ",0)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '09:47:57.295', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (693, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,4,FALSE,68)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO PRESTAMO SDASDA 4 MARACAS 1 ', '09:48:22.92', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (694, 'BIEN', '(22,42,33,1,323,7,MARACAS,9,9,5,"TRUE ",0)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '09:48:44.671', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (695, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,5,"TRUE ",0)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINÓ EL MOVIMIENTO PRESTAR 41 MARACAS 4', '09:48:59.765', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (696, 'BIEN', '(22,41,33,1,323,7,MARACAS,9,9,4,FALSE,69)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO PRESTAMO ASASDASD 4 MARACAS 2 ', '09:54:47.801', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (712, 'MODELO', '(6,"STUDIO 5C",18,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:24.671', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (713, 'MODELO', '(8,MV012R,16,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:38.515', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (714, 'MODELO', '(9,PRO2,16,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:44.39', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (715, 'MODELO', '(10,"STUDENT I",15,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:49.969', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (716, 'MODELO', '(7,BEGGINERS,15,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '17:13:54.453', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (717, 'BIEN', '(03332,55,34,1,01922,7,FORROS,13,7,5,FALSE,)', 'postgres', '2018-10-21', NULL, 'MODIFICAR', 'EL USUARIO ADMIN ELIMINO EL BIEN FORROS 03332', '18:05:56.354', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (718, 'PROFESOR', '(3,3,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '18:36:04.45', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (719, 'PROFESOR', '(3,4,"TRUE ")', 'postgres', '2018-10-21', NULL, 'MODIFICAR', NULL, '21:14:51.921', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (720, 'BIEN', '(03332,46,34,1,01922,7,FORROS,13,7,2,FALSE,)', 'postgres', '2018-10-23', NULL, 'MODIFICAR', NULL, '23:55:05.86', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (721, 'BIEN', '(03332,47,34,1,01922,7,FORROS,13,7,2,FALSE,)', 'postgres', '2018-10-23', NULL, 'MODIFICAR', NULL, '23:55:06.095', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (722, 'BIEN', '(03332,48,34,1,01922,7,FORROS,13,7,2,FALSE,)', 'postgres', '2018-10-23', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR POR QUE SI 4 FORROS 3 ', '23:55:06.142', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (723, 'ESTUDIANTE', '(25,,,5,"TRUE ")', 'postgres', '2018-10-24', NULL, 'REGISTRAR', NULL, '16:21:40.067', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (724, 'ESTUDIANTE', '(23,1,6,2,"TRUE ")', 'postgres', '2018-10-24', NULL, 'MODIFICAR', NULL, '16:21:48.458', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (725, 'ESTUDIANTE', '(24,7,10,14,"TRUE ")', 'postgres', '2018-10-24', NULL, 'MODIFICAR', NULL, '16:21:56.974', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (726, 'ESTUDIANTE', '(25,,12,5,"TRUE ")', 'postgres', '2018-10-24', NULL, 'MODIFICAR', NULL, '16:22:11.802', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (727, 'ESTUDIANTE', '(25,1,12,5,"TRUE ")', 'postgres', '2018-10-24', NULL, 'MODIFICAR', NULL, '16:23:18.882', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (728, 'BIEN', '(03332,49,34,1,01922,7,FORROS,13,7,2,FALSE,)', 'postgres', '2018-10-24', NULL, 'MODIFICAR', NULL, '23:31:30.976', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (729, 'BIEN', '(03332,50,34,1,01922,7,FORROS,13,7,2,FALSE,)', 'postgres', '2018-10-24', NULL, 'MODIFICAR', NULL, '23:31:31.288', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (730, 'BIEN', '(03332,51,34,1,01922,7,FORROS,13,7,2,FALSE,)', 'postgres', '2018-10-24', NULL, 'MODIFICAR', 'EL USUARIO ADMIN REGISTRO MOVIMIENTO ASIGNAR SADASDASD 4 FORROS 3 ', '23:31:31.335', 4);


--
-- TOC entry 2418 (class 0 OID 0)
-- Dependencies: 174
-- Name: bitacora_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bitacora_id_seq', 730, true);


--
-- TOC entry 2315 (class 0 OID 24857)
-- Dependencies: 175
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cargo (id, nombrecar, descripcion, codcar, eliminacion) VALUES (1, 'ADMINISTRADOR', 'REGISTRA ESTUDIANTES', NULL, 'TRUE ');
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar, eliminacion) VALUES (22, 'SECRETARIA', 'REGISTRAR ESTUDIANTE', NULL, 'TRUE ');
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar, eliminacion) VALUES (30, 'DOCENTE', 'PREPARA A LOS ESTUDIANTES', NULL, 'TRUE ');
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar, eliminacion) VALUES (29, 'DIRECTOR', 'ADMINISTRA EL PLANTEL', NULL, 'TRUE ');


--
-- TOC entry 2419 (class 0 OID 0)
-- Dependencies: 176
-- Name: cargo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cargo_id_seq', 36, true);


--
-- TOC entry 2317 (class 0 OID 24862)
-- Dependencies: 177
-- Data for Name: catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (4, 'CATEDRA', 'DESCRICION', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (6, 'VIOLIN', 'VIOLIN', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (8, 'VIOLONCELLO', 'VIOLONCELLO', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (9, 'CONTRABAJO', 'CONTRABAJO', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (10, 'FLAUTA', 'FLAUTA', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (11, 'OBOE', 'OBOE', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (12, 'TUBA', 'TUBA', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (13, 'HISTORIA DE LA MUSICA', 'HISTORIA DE LA MUSICA', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (14, 'LENGUAJE MUSICAL', 'LENGUAJE MUSICAL', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (15, 'TROMPETA', 'TROMPETA', 'TRUE ');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion, eliminacion) VALUES (17, 'VIOLA', 'VIOLA', 'TRUE ');


--
-- TOC entry 2420 (class 0 OID 0)
-- Dependencies: 178
-- Name: catedra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.catedra_id_seq', 17, true);


--
-- TOC entry 2319 (class 0 OID 24867)
-- Dependencies: 179
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.empleado (id, id_usuario, id_cargo, id_persona, eliminacion) VALUES (6, 4, NULL, 2, NULL);


--
-- TOC entry 2421 (class 0 OID 0)
-- Dependencies: 180
-- Name: empleado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_seq', 6, true);


--
-- TOC entry 2422 (class 0 OID 0)
-- Dependencies: 181
-- Name: empleado_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_usuario_seq', 2, true);


--
-- TOC entry 2322 (class 0 OID 24874)
-- Dependencies: 182
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estudiante (id_estudiante, id_representante, id_catedra, id_persona, eliminacion) VALUES (23, 1, 6, 2, 'TRUE ');
INSERT INTO public.estudiante (id_estudiante, id_representante, id_catedra, id_persona, eliminacion) VALUES (24, 7, 10, 14, 'TRUE ');
INSERT INTO public.estudiante (id_estudiante, id_representante, id_catedra, id_persona, eliminacion) VALUES (25, 1, 12, 5, 'TRUE ');


--
-- TOC entry 2323 (class 0 OID 24877)
-- Dependencies: 183
-- Data for Name: estudiante_catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estudiante_catedra (id_est, id_estudiante, id_catedra) VALUES (3, 2, 4);
INSERT INTO public.estudiante_catedra (id_est, id_estudiante, id_catedra) VALUES (4, 2, 6);


--
-- TOC entry 2423 (class 0 OID 0)
-- Dependencies: 184
-- Name: estudiante_catedra_id_est_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estudiante_catedra_id_est_seq', 4, true);


--
-- TOC entry 2424 (class 0 OID 0)
-- Dependencies: 185
-- Name: estudiante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estudiante_id_seq', 25, true);


--
-- TOC entry 2326 (class 0 OID 24884)
-- Dependencies: 186
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (7, 'SIN MARCA', 'ARTESANALES', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (2, 'NIKE', 'ZAPATOS NIKE', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (9, 'YAMAHA', 'YAMAHA', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (10, 'IBANEZ', 'IBANEZ', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (11, 'FENDER', 'FENDER', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (12, 'SQUIER', 'SQUIER', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (13, 'SCHECTER', 'SCHECTER', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (14, 'NOVRE', 'NOVRE', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (15, 'STENTOR', 'STENTOR', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (16, 'LARRC', 'LARC', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (17, 'BERBERIEN', 'BERBERIEN', 'TRUE ');
INSERT INTO public.marca (id_marca, nombrema, descripcion, eliminacion) VALUES (18, 'ASTOR', 'ASTOR', 'TRUE ');


--
-- TOC entry 2425 (class 0 OID 0)
-- Dependencies: 187
-- Name: marca_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marca_id_seq', 28, true);


--
-- TOC entry 2328 (class 0 OID 24889)
-- Dependencies: 188
-- Data for Name: modelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (1, 'modelo', 6, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (2, 'nombreM', 2, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (3, 'ALTERNATIVO', 8, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (4, 'ROSEWOOD', 9, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (5, 'VIBE 60', 10, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (6, 'STUDIO 5C', 18, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (8, 'MV012R', 16, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (9, 'PRO2', 16, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (10, 'STUDENT I', 15, 'TRUE ');
INSERT INTO public.modelo (id_modelo, nombremo, id_marca, eliminacion) VALUES (7, 'BEGGINERS', 15, 'TRUE ');


--
-- TOC entry 2426 (class 0 OID 0)
-- Dependencies: 189
-- Name: modelo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modelo_id_seq', 10, true);


--
-- TOC entry 2330 (class 0 OID 24894)
-- Dependencies: 190
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (1, 'DESC', 'MODULO', 'TRUE ');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (2, 'DDDDD', 'MODULO', 'TRUE ');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (3, 'DEEE', 'OTRO', 'TRUE ');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (4, 'EQEQEQWE', 'ASDADADASD', 'TRUE ');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (5, 'ORQUESTA PRE-INFANTIL M.R.C', 'PRE-INFANTIL MANUEL RODRIGUEZ CARDENAS', 'TRUE ');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (6, 'CONSERVATORIO B.E.M', 'CONSERVATORIO BLANCA ESTRELLA DE MESCOLI', 'TRUE ');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd, eliminacion) VALUES (7, 'LAS PIEDRAS', 'MODULO LAS PIEDRAS', 'TRUE ');


--
-- TOC entry 2427 (class 0 OID 0)
-- Dependencies: 191
-- Name: modulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modulo_id_seq', 7, true);


--
-- TOC entry 2332 (class 0 OID 24899)
-- Dependencies: 192
-- Data for Name: movimiento_bien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa, cantidad, id_mp_estudiante, id_mp_catedra, id_mp_tprestamo, fecha_d, nombrebien) VALUES (49, '2018-10-20', 'DASDASDASDAS', 4, 25, 2, 6, 5, 7, '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa, cantidad, id_mp_estudiante, id_mp_catedra, id_mp_tprestamo, fecha_d, nombrebien) VALUES (50, '2018-10-20', 'ASDASDASDASD', 4, 29, 3, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa, cantidad, id_mp_estudiante, id_mp_catedra, id_mp_tprestamo, fecha_d, nombrebien) VALUES (69, '2018-10-21', 'ASASDASD', 4, 41, 4, 6, NULL, NULL, '2', 2, 6, 2, '2018-10-22', 'MARACAS');
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa, cantidad, id_mp_estudiante, id_mp_catedra, id_mp_tprestamo, fecha_d, nombrebien) VALUES (70, '2018-10-21', 'ADASDASD', 4, 42, 4, 6, NULL, NULL, '2', 2, 6, 1, '2018-10-21', 'MARACAS');
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa, cantidad, id_mp_estudiante, id_mp_catedra, id_mp_tprestamo, fecha_d, nombrebien) VALUES (71, '2018-10-23', 'POR QUE SI', 4, 54, 2, 6, 5, 7, '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa, cantidad, id_mp_estudiante, id_mp_catedra, id_mp_tprestamo, fecha_d, nombrebien) VALUES (72, '2018-10-24', 'SADASDASD', 4, 54, 2, 6, 5, 7, '3', NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 2428 (class 0 OID 0)
-- Dependencies: 193
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_bien_id_mov_bien_seq', 72, true);


--
-- TOC entry 2365 (class 0 OID 33545)
-- Dependencies: 232
-- Data for Name: movimiento_prestamo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2429 (class 0 OID 0)
-- Dependencies: 231
-- Name: movimiento_prestamo_id_mv_prestamo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_prestamo_id_mv_prestamo_seq', 2, true);


--
-- TOC entry 2334 (class 0 OID 24904)
-- Dependencies: 194
-- Data for Name: opcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.opcion (id, codopc, nombre) VALUES (1, '1', 'ESTUDIANTE');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (2, '17', 'PERFIL');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (3, '2', 'USUARIO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (5, '6', 'PROFESOR');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (6, '11', 'EMPLEADO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (7, '5', 'REPRESENTANTE');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (8, '12', 'MODELO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (9, '13', 'MARCA');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (10, '3', 'CATEDRA');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (11, '10', 'PROGRAMA');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (12, '15', 'MODULO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (13, '7', 'BIEN');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (14, '8', 'TIPO BIEN');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (15, '4', 'CARGO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (16, '9', 'TIPO SINIESTRO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (17, '14', 'TIPO INSTRUMENTO');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (20, '18', 'BITACORA');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (21, '19', 'RESPALDAR Y RESTAURAR');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (18, '16', 'ASIGNAR BIEN');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (24, '20', 'DESINCORPORAR BIEN');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (25, '21', 'PRESTAMO');


--
-- TOC entry 2430 (class 0 OID 0)
-- Dependencies: 195
-- Name: opcion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcion_id_seq', 25, true);


--
-- TOC entry 2336 (class 0 OID 24909)
-- Dependencies: 196
-- Data for Name: opcxperfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (5, '1', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (30, '19', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (31, '17', '6   ');


--
-- TOC entry 2431 (class 0 OID 0)
-- Dependencies: 197
-- Name: opcxperfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcxperfil_id_seq', 31, true);


--
-- TOC entry 2338 (class 0 OID 24914)
-- Dependencies: 198
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.perfil (id_perfil, codperfil, descrip, estatus, eliminacion) VALUES (6, '1', 'ADMINISTRADOR', 's', 'TRUE ');


--
-- TOC entry 2432 (class 0 OID 0)
-- Dependencies: 199
-- Name: perfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.perfil_id_seq', 4, true);


--
-- TOC entry 2340 (class 0 OID 24920)
-- Dependencies: 200
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (3, 'GOCHO', 'TORRES', '6601992', '04125083262', 'M', 'YARITAGUA', '1996-08-05', 4, 'V', 3, NULL, NULL, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (4, 'JOSE', 'GOMEZ', '6601992', '04265028463', 'M', 'SAN JOSE', '1996-06-04', 3, 'V', 3, NULL, 1, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (5, 'ANGEL', 'PEREZ', '15624789', '04125789451', 'M', 'URB TERMO YARACUY', '2002-07-11', 2, 'V', NULL, NULL, 1, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (1, 'CESAR', 'MOLINA', '25646854', '04125083262', 'M', 'YARITAGUA', '1996-08-05', 5, 'V', NULL, 23, NULL, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (2, 'AUGUSTO', 'MOLINA', '6844714', '04125083262', 'M', 'YARITAGUA LA PASTORA', '1996-08-05', 2, 'V', NULL, NULL, NULL, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (7, 'EEEEEE', 'EEEEEE', '222222', '222222', 'M', 'EEEEEEEEEEE', '2018-10-03', 5, NULL, NULL, NULL, NULL, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (14, 'HECTOR JULIO', 'PEREZ SALAS', '29789658', '04125269785', 'M', 'LA TERMO YARACUY', '2001-07-03', 2, 'V', NULL, NULL, 7, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (15, 'CESAR AUGUSTO', 'MOLINA TORRES', '25646854', '04125083262', 'M', 'LA PASTORA', '1996-08-05', NULL, 'V', NULL, NULL, NULL, 'TRUE ');
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante, eliminacion) VALUES (16, 'CESAR AUGUSTO', 'MOLINA TORRES', '25646854', '04125083262', 'M', 'LA PASTORA', '1996-08-05', NULL, 'V', NULL, NULL, NULL, 'TRUE ');


--
-- TOC entry 2433 (class 0 OID 0)
-- Dependencies: 201
-- Name: persona_id_persona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persona_id_persona_seq', 16, true);


--
-- TOC entry 2342 (class 0 OID 24925)
-- Dependencies: 202
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.profesor (id_profesor, id_personapro, eliminacion) VALUES (3, 4, 'TRUE ');


--
-- TOC entry 2343 (class 0 OID 24928)
-- Dependencies: 203
-- Data for Name: profesor_catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.profesor_catedra (id_prof_cate, id_profesor, id_catedra) VALUES (1, 4, 4);
INSERT INTO public.profesor_catedra (id_prof_cate, id_profesor, id_catedra) VALUES (4, 4, 6);


--
-- TOC entry 2434 (class 0 OID 0)
-- Dependencies: 204
-- Name: profesor_catedra_id_prof_cate_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_catedra_id_prof_cate_seq', 4, true);


--
-- TOC entry 2345 (class 0 OID 24933)
-- Dependencies: 205
-- Data for Name: profesor_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2435 (class 0 OID 0)
-- Dependencies: 206
-- Name: profesor_estudiante_id_profestudiante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_estudiante_id_profestudiante_seq', 1, false);


--
-- TOC entry 2436 (class 0 OID 0)
-- Dependencies: 207
-- Name: profesor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_id_seq', 3, true);


--
-- TOC entry 2348 (class 0 OID 24940)
-- Dependencies: 208
-- Data for Name: programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.programa (id_programa, nombrepro, descripcionpro, id_modulo, eliminacion) VALUES (5, 'PROGRA ESPACIAL', 'DESDE MI CASA', 1, 'TRUE ');
INSERT INTO public.programa (id_programa, nombrepro, descripcionpro, id_modulo, eliminacion) VALUES (7, 'PROGRAMA INTERNACIONAL', 'DESCRIPCION', 5, 'TRUE ');


--
-- TOC entry 2437 (class 0 OID 0)
-- Dependencies: 209
-- Name: programa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.programa_id_seq', 7, true);


--
-- TOC entry 2350 (class 0 OID 24945)
-- Dependencies: 210
-- Data for Name: representante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.representante (id_representante, id_persona, id_estudiante, eliminacion) VALUES (1, 1, 23, NULL);
INSERT INTO public.representante (id_representante, id_persona, id_estudiante, eliminacion) VALUES (2, 7, NULL, 'TRUE ');


--
-- TOC entry 2438 (class 0 OID 0)
-- Dependencies: 211
-- Name: representante_id_representante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.representante_id_representante_seq', 2, true);


--
-- TOC entry 2352 (class 0 OID 24950)
-- Dependencies: 212
-- Data for Name: tipo_movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move, eliminacion) VALUES (2, 'ASIGNAR', NULL);
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move, eliminacion) VALUES (3, 'DESINCORPORAR', NULL);
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move, eliminacion) VALUES (1, 'REPARAR', NULL);
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move, eliminacion) VALUES (4, 'PRESTAR', NULL);
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move, eliminacion) VALUES (5, 'DISPONIBLE', NULL);


--
-- TOC entry 2439 (class 0 OID 0)
-- Dependencies: 213
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_movimiento_id_tipo_move_seq', 5, true);


--
-- TOC entry 2367 (class 0 OID 33551)
-- Dependencies: 234
-- Data for Name: tipo_prestamo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_prestamo (id_tipo_prestamo, nombre_tprestamo, eliminacion, descripciontp, dias) VALUES (1, 'CORTO PLAZO', 'TRUE', 'CORTO PLAZO', '5 ');
INSERT INTO public.tipo_prestamo (id_tipo_prestamo, nombre_tprestamo, eliminacion, descripciontp, dias) VALUES (2, 'LARGO PLAZO', 'TRUE', 'LARGO PLAZO', '30');


--
-- TOC entry 2440 (class 0 OID 0)
-- Dependencies: 233
-- Name: tipo_prestamo_id_tipo_prestamo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_prestamo_id_tipo_prestamo_seq', 2, true);


--
-- TOC entry 2354 (class 0 OID 24955)
-- Dependencies: 214
-- Data for Name: tipo_siniestro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts, eliminacion) VALUES (1, 'ROBADO', 'DESCRIPCION', 'TRUE ');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts, eliminacion) VALUES (2, 'EXTRAVIADO', 'NO TIENE PRESENCIA', 'TRUE ');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts, eliminacion) VALUES (3, 'QUEMADO', 'PROBLEMAS POR FUEGO', 'TRUE ');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts, eliminacion) VALUES (4, 'DAÑADO', 'PROBLEMAS FISICOS', 'TRUE ');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts, eliminacion) VALUES (5, 'REPARAR', 'POR CAMBIO DE PIESAS', 'TRUE ');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts, eliminacion) VALUES (6, 'SIN TIPO SINIESTRO', 'VALOR NULO O POR DEFECTO', 'TRUE ');


--
-- TOC entry 2441 (class 0 OID 0)
-- Dependencies: 215
-- Name: tipo_siniestro_id_tipos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_siniestro_id_tipos_seq', 6, true);


--
-- TOC entry 2356 (class 0 OID 24960)
-- Dependencies: 216
-- Data for Name: tipobien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipobien (id_tipob, nombretb, descripcion, eliminacion) VALUES (3, 'CUERDAS', 'INSTRUMENTO DE HILOS', 'TRUE ');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion, eliminacion) VALUES (4, 'MADERA', 'DESPCAD', 'TRUE ');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion, eliminacion) VALUES (5, 'DE AIRE', 'DEASDASDASASDA', 'TRUE ');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion, eliminacion) VALUES (6, 'MUEBLES', 'TODO LO QUE INCLUYA M', 'TRUE ');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion, eliminacion) VALUES (7, 'INSTRUMENTALES', 'TODO SOBRE INSTRUMENT', 'TRUE ');


--
-- TOC entry 2442 (class 0 OID 0)
-- Dependencies: 217
-- Name: tipobien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipobien_id_seq', 7, true);


--
-- TOC entry 2358 (class 0 OID 24965)
-- Dependencies: 218
-- Data for Name: tipoinstrumento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (1, 'NOMBRE', 'DESCRIPCION');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (3, 'CUERDA', 'ASDADASDASD');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (4, 'DE AIRE', 'SADASDASDDASD');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (5, 'DE MADERA', 'QWEQWEQWE');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (6, 'CUERDA', 'INSTRUMENTOS DE CUERDAS');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (7, 'VIENTO', 'INSTRUMENTOS DE VIENTO');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (8, 'PERCUSION', 'INSTRUMENTOS DE PERCUSION');
INSERT INTO public.tipoinstrumento (id_tipo_instrumento, nombreti, descripcionti) VALUES (9, 'ELECTRICOS', 'INSTRUMENTOS ELECTRICOS');


--
-- TOC entry 2443 (class 0 OID 0)
-- Dependencies: 219
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipoinstrumento_id_seq', 9, true);


--
-- TOC entry 2360 (class 0 OID 24970)
-- Dependencies: 220
-- Data for Name: tipopersona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipopersona (id_tipopersona, nombretipoper, eliminacion) VALUES (1, 'EMPLEADO', NULL);
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper, eliminacion) VALUES (2, 'ESTUDIANTE', NULL);
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper, eliminacion) VALUES (3, 'PROFESOR', NULL);
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper, eliminacion) VALUES (4, 'USUARIO', NULL);
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper, eliminacion) VALUES (5, 'REPRESENTANTE', NULL);


--
-- TOC entry 2444 (class 0 OID 0)
-- Dependencies: 221
-- Name: tipopersona_id_tipopersona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipopersona_id_tipopersona_seq', 5, true);


--
-- TOC entry 2362 (class 0 OID 24975)
-- Dependencies: 222
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, clave, id_perfil, login, status, id_persona, eliminacion, id_usu_cargo) VALUES (4, '123', 6, 'ADMIN', 'TRUE', 3, 'TRUE ', 1);


--
-- TOC entry 2445 (class 0 OID 0)
-- Dependencies: 223
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 4, true);


--
-- TOC entry 2069 (class 2606 OID 25060)
-- Name: bien_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_id_key UNIQUE (id);


--
-- TOC entry 2071 (class 2606 OID 25062)
-- Name: bien_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_pkey PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 25064)
-- Name: cargo_nombrecar_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_nombrecar_key UNIQUE (nombrecar);


--
-- TOC entry 2075 (class 2606 OID 25066)
-- Name: cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 25068)
-- Name: catedra_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.catedra
    ADD CONSTRAINT catedra_id_key UNIQUE (id_catedra);


--
-- TOC entry 2095 (class 2606 OID 25070)
-- Name: codopc; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT codopc PRIMARY KEY (codopc);


--
-- TOC entry 2099 (class 2606 OID 25072)
-- Name: codperfil; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT codperfil PRIMARY KEY (codperfil);


--
-- TOC entry 2079 (class 2606 OID 25074)
-- Name: empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id);


--
-- TOC entry 2085 (class 2606 OID 25076)
-- Name: estudiante_catedra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.estudiante_catedra
    ADD CONSTRAINT estudiante_catedra_pkey PRIMARY KEY (id_est);


--
-- TOC entry 2081 (class 2606 OID 41755)
-- Name: estudiante_id_persona_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_persona_key UNIQUE (id_persona);


--
-- TOC entry 2083 (class 2606 OID 25078)
-- Name: estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id_estudiante);


--
-- TOC entry 2097 (class 2606 OID 25080)
-- Name: idopcper; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT idopcper PRIMARY KEY (id);


--
-- TOC entry 2087 (class 2606 OID 25084)
-- Name: modulo_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_id_key UNIQUE (id_modulo);


--
-- TOC entry 2089 (class 2606 OID 25086)
-- Name: modulo_id_modulo_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_id_modulo_key UNIQUE (id_modulo);


--
-- TOC entry 2091 (class 2606 OID 25088)
-- Name: modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_pkey PRIMARY KEY (id_modulo);


--
-- TOC entry 2093 (class 2606 OID 25090)
-- Name: movimiento_bien_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_pkey PRIMARY KEY (id_mov_bien);


--
-- TOC entry 2145 (class 2606 OID 41763)
-- Name: movimiento_prestamo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.movimiento_prestamo
    ADD CONSTRAINT movimiento_prestamo_pkey PRIMARY KEY (id_mv_prestamo);


--
-- TOC entry 2101 (class 2606 OID 25092)
-- Name: perfil_codperfil_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_codperfil_key UNIQUE (codperfil);


--
-- TOC entry 2103 (class 2606 OID 25094)
-- Name: perfil_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_id_key UNIQUE (id_perfil);


--
-- TOC entry 2105 (class 2606 OID 25096)
-- Name: persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2113 (class 2606 OID 25098)
-- Name: profesor_catedra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.profesor_catedra
    ADD CONSTRAINT profesor_catedra_pkey PRIMARY KEY (id_prof_cate);


--
-- TOC entry 2115 (class 2606 OID 25100)
-- Name: profesor_estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.profesor_estudiante
    ADD CONSTRAINT profesor_estudiante_pkey PRIMARY KEY (id_profestudiante);


--
-- TOC entry 2107 (class 2606 OID 25102)
-- Name: profesor_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_key UNIQUE (id_profesor);


--
-- TOC entry 2109 (class 2606 OID 49913)
-- Name: profesor_id_personapro_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_personapro_key UNIQUE (id_personapro);


--
-- TOC entry 2111 (class 2606 OID 25104)
-- Name: profesor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_pkey PRIMARY KEY (id_profesor);


--
-- TOC entry 2117 (class 2606 OID 25106)
-- Name: programa_id_programa_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_id_programa_key UNIQUE (id_programa);


--
-- TOC entry 2119 (class 2606 OID 25108)
-- Name: programa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id_programa);


--
-- TOC entry 2121 (class 2606 OID 25326)
-- Name: representante_id_persona_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_persona_key UNIQUE (id_persona);


--
-- TOC entry 2123 (class 2606 OID 25110)
-- Name: representante_id_representante_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_representante_key UNIQUE (id_representante);


--
-- TOC entry 2125 (class 2606 OID 25112)
-- Name: representante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_pkey PRIMARY KEY (id_representante);


--
-- TOC entry 2127 (class 2606 OID 25114)
-- Name: tipo_movimiento_id_tipo_move_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_id_tipo_move_key UNIQUE (id_tipo_move);


--
-- TOC entry 2129 (class 2606 OID 25116)
-- Name: tipo_movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_pkey PRIMARY KEY (id_tipo_move);


--
-- TOC entry 2131 (class 2606 OID 25118)
-- Name: tipo_siniestro_id_tipos_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipo_siniestro
    ADD CONSTRAINT tipo_siniestro_id_tipos_key UNIQUE (id_tipos);


--
-- TOC entry 2133 (class 2606 OID 25120)
-- Name: tipo_siniestro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipo_siniestro
    ADD CONSTRAINT tipo_siniestro_pkey PRIMARY KEY (id_tipos);


--
-- TOC entry 2135 (class 2606 OID 25122)
-- Name: tipobien_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipobien
    ADD CONSTRAINT tipobien_id_key UNIQUE (id_tipob);


--
-- TOC entry 2137 (class 2606 OID 25124)
-- Name: tipopersona_id_tipopersona_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipopersona
    ADD CONSTRAINT tipopersona_id_tipopersona_key UNIQUE (id_tipopersona);


--
-- TOC entry 2139 (class 2606 OID 25126)
-- Name: tipopersona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.tipopersona
    ADD CONSTRAINT tipopersona_pkey PRIMARY KEY (id_tipopersona);


--
-- TOC entry 2141 (class 2606 OID 25128)
-- Name: usuario_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_key UNIQUE (id);


--
-- TOC entry 2143 (class 2606 OID 25304)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2181 (class 2620 OID 25129)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.marca FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2178 (class 2620 OID 25130)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.catedra FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2179 (class 2620 OID 25131)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.empleado FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2180 (class 2620 OID 25132)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.estudiante FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2182 (class 2620 OID 25133)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.modelo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2183 (class 2620 OID 25134)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.modulo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2184 (class 2620 OID 25135)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.perfil FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2187 (class 2620 OID 25136)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.programa FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2188 (class 2620 OID 25137)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.representante FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2189 (class 2620 OID 25138)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.tipobien FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2190 (class 2620 OID 25139)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.tipoinstrumento FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2177 (class 2620 OID 25140)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.cargo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2176 (class 2620 OID 25141)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.bien FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2191 (class 2620 OID 25142)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE ON public.usuario FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2186 (class 2620 OID 25143)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.profesor FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2185 (class 2620 OID 25300)
-- Name: tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.persona FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2146 (class 2606 OID 25144)
-- Name: bien_id_tipob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_id_tipob_fkey FOREIGN KEY (id_tipob) REFERENCES public.tipobien(id_tipob);


--
-- TOC entry 2147 (class 2606 OID 25149)
-- Name: bien_tipo_move_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_tipo_move_fkey FOREIGN KEY (tipo_move) REFERENCES public.tipo_movimiento(id_tipo_move);


--
-- TOC entry 2159 (class 2606 OID 25154)
-- Name: codopc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT codopc FOREIGN KEY (codopc) REFERENCES public.opcion(codopc);


--
-- TOC entry 2148 (class 2606 OID 25159)
-- Name: empleado_id_cargo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_cargo_fkey FOREIGN KEY (id_cargo) REFERENCES public.cargo(id);


--
-- TOC entry 2149 (class 2606 OID 25164)
-- Name: empleado_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- TOC entry 2153 (class 2606 OID 25169)
-- Name: estudiante_catedra_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra
    ADD CONSTRAINT estudiante_catedra_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2154 (class 2606 OID 41756)
-- Name: estudiante_catedra_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra
    ADD CONSTRAINT estudiante_catedra_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_persona);


--
-- TOC entry 2150 (class 2606 OID 25179)
-- Name: estudiante_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2151 (class 2606 OID 25184)
-- Name: estudiante_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);


--
-- TOC entry 2152 (class 2606 OID 25332)
-- Name: estudiante_id_representante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_representante_fkey FOREIGN KEY (id_representante) REFERENCES public.representante(id_persona);


--
-- TOC entry 2155 (class 2606 OID 25194)
-- Name: movimiento_bien_id_bien_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_bien_fkey FOREIGN KEY (id_bien) REFERENCES public.bien(id);


--
-- TOC entry 2156 (class 2606 OID 25199)
-- Name: movimiento_bien_id_mv_programa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_mv_programa_fkey FOREIGN KEY (id_mv_programa) REFERENCES public.programa(id_programa);


--
-- TOC entry 2157 (class 2606 OID 25204)
-- Name: movimiento_bien_id_tipo_movimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_tipo_movimiento_fkey FOREIGN KEY (id_tipo_movimiento) REFERENCES public.tipo_siniestro(id_tipos);


--
-- TOC entry 2158 (class 2606 OID 25209)
-- Name: movimiento_bien_id_tipo_siniestro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_tipo_siniestro_fkey FOREIGN KEY (id_tipo_siniestro) REFERENCES public.tipo_siniestro(id_tipos);


--
-- TOC entry 2175 (class 2606 OID 41764)
-- Name: movimiento_prestamo_id_mp_tipomv_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_prestamo
    ADD CONSTRAINT movimiento_prestamo_id_mp_tipomv_fkey FOREIGN KEY (id_mp_tipomv) REFERENCES public.tipo_movimiento(id_tipo_move);


--
-- TOC entry 2160 (class 2606 OID 25214)
-- Name: persona_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2163 (class 2606 OID 25327)
-- Name: persona_id_per_representante_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_per_representante_fkey1 FOREIGN KEY (id_per_representante) REFERENCES public.representante(id_persona);


--
-- TOC entry 2161 (class 2606 OID 25224)
-- Name: persona_id_profesor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_profesor_fkey FOREIGN KEY (id_profesor) REFERENCES public.profesor(id_profesor);


--
-- TOC entry 2162 (class 2606 OID 25229)
-- Name: persona_tipoper_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_tipoper_fkey FOREIGN KEY (tipoper) REFERENCES public.tipopersona(id_tipopersona);


--
-- TOC entry 2165 (class 2606 OID 25234)
-- Name: profesor_catedra_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra
    ADD CONSTRAINT profesor_catedra_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2166 (class 2606 OID 49924)
-- Name: profesor_catedra_id_profesor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra
    ADD CONSTRAINT profesor_catedra_id_profesor_fkey FOREIGN KEY (id_profesor) REFERENCES public.profesor(id_personapro);


--
-- TOC entry 2167 (class 2606 OID 25244)
-- Name: profesor_estudiante_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante
    ADD CONSTRAINT profesor_estudiante_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2168 (class 2606 OID 25249)
-- Name: profesor_estudiante_id_profesor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante
    ADD CONSTRAINT profesor_estudiante_id_profesor_fkey FOREIGN KEY (id_profesor) REFERENCES public.profesor(id_profesor);


--
-- TOC entry 2164 (class 2606 OID 25254)
-- Name: profesor_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_persona_fkey FOREIGN KEY (id_personapro) REFERENCES public.persona(id_persona);


--
-- TOC entry 2169 (class 2606 OID 25259)
-- Name: programa_id_modulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_id_modulo_fkey FOREIGN KEY (id_modulo) REFERENCES public.modulo(id_modulo);


--
-- TOC entry 2170 (class 2606 OID 25264)
-- Name: representante_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2171 (class 2606 OID 25269)
-- Name: representante_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);


--
-- TOC entry 2172 (class 2606 OID 25274)
-- Name: usuario_id_perfil_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES public.perfil(id_perfil);


--
-- TOC entry 2173 (class 2606 OID 25279)
-- Name: usuario_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);


--
-- TOC entry 2174 (class 2606 OID 25305)
-- Name: usuario_id_usu_cargo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_usu_cargo_fkey FOREIGN KEY (id_usu_cargo) REFERENCES public.cargo(id);


--
-- TOC entry 2375 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 188
-- Name: TABLE modelo; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.modelo FROM PUBLIC;
REVOKE ALL ON TABLE public.modelo FROM postgres;
GRANT ALL ON TABLE public.modelo TO postgres;
GRANT UPDATE ON TABLE public.modelo TO PUBLIC;


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 194
-- Name: TABLE opcion; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.opcion FROM PUBLIC;
REVOKE ALL ON TABLE public.opcion FROM postgres;
GRANT ALL ON TABLE public.opcion TO postgres;
GRANT ALL ON TABLE public.opcion TO PUBLIC;


--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE opcxperfil; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.opcxperfil FROM PUBLIC;
REVOKE ALL ON TABLE public.opcxperfil FROM postgres;
GRANT ALL ON TABLE public.opcxperfil TO postgres;
GRANT ALL ON TABLE public.opcxperfil TO PUBLIC;


--
-- TOC entry 2412 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE tipobien; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.tipobien FROM PUBLIC;
REVOKE ALL ON TABLE public.tipobien FROM postgres;
GRANT ALL ON TABLE public.tipobien TO postgres;
GRANT UPDATE ON TABLE public.tipobien TO PUBLIC;


-- Completed on 2018-10-24 23:50:06

--
-- PostgreSQL database dump complete
--

