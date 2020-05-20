--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

-- Started on 2018-10-17 12:06:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 261 (class 1255 OID 42478)
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
-- TOC entry 260 (class 1255 OID 108932)
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
-- TOC entry 190 (class 1259 OID 16836)
-- Name: bien; Type: TABLE; Schema: public; Owner: postgres
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
    tipo_move integer
);


ALTER TABLE public.bien OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16839)
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
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 191
-- Name: bien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bien_id_seq OWNED BY public.bien.id;


--
-- TOC entry 210 (class 1259 OID 17768)
-- Name: bitacora; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 211 (class 1259 OID 17771)
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
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 211
-- Name: bitacora_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bitacora_id_seq OWNED BY public.bitacora.id;


--
-- TOC entry 193 (class 1259 OID 16847)
-- Name: cargo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cargo (
    id integer NOT NULL,
    nombrecar character varying(20),
    descripcion character varying(30),
    codcar integer
);


ALTER TABLE public.cargo OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16845)
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
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 192
-- Name: cargo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cargo_id_seq OWNED BY public.cargo.id;


--
-- TOC entry 189 (class 1259 OID 16827)
-- Name: catedra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.catedra (
    id_catedra integer NOT NULL,
    nombrec character varying(30),
    descripcion character varying(50)
);


ALTER TABLE public.catedra OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16851)
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
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 194
-- Name: catedra_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.catedra_id_seq OWNED BY public.catedra.id_catedra;


--
-- TOC entry 200 (class 1259 OID 16897)
-- Name: empleado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleado (
    id integer NOT NULL,
    id_usuario integer NOT NULL,
    id_cargo integer,
    id_persona integer
);


ALTER TABLE public.empleado OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16900)
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
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 201
-- Name: empleado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_seq OWNED BY public.empleado.id;


--
-- TOC entry 216 (class 1259 OID 34367)
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
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 216
-- Name: empleado_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_usuario_seq OWNED BY public.empleado.id_usuario;


--
-- TOC entry 185 (class 1259 OID 16413)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudiante (
    id_estudiante integer NOT NULL,
    id_representante integer,
    id_catedra integer,
    id_persona integer
);


ALTER TABLE public.estudiante OWNER TO postgres;

--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN estudiante.id_persona; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.estudiante.id_persona IS 'referenciaa a tabla personar';


--
-- TOC entry 230 (class 1259 OID 100200)
-- Name: estudiante_catedra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudiante_catedra (
    id_est integer NOT NULL,
    id_estudiante integer,
    id_catedra integer
);


ALTER TABLE public.estudiante_catedra OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 100198)
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
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 229
-- Name: estudiante_catedra_id_est_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estudiante_catedra_id_est_seq OWNED BY public.estudiante_catedra.id_est;


--
-- TOC entry 186 (class 1259 OID 16416)
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
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 186
-- Name: estudiante_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estudiante_id_seq OWNED BY public.estudiante.id_estudiante;


--
-- TOC entry 207 (class 1259 OID 16921)
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca (
    id_marca integer NOT NULL,
    nombrema character varying(20),
    descripcion character varying(30)
);


ALTER TABLE public.marca OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16919)
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
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 206
-- Name: marca_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.marca_id_seq OWNED BY public.marca.id_marca;


--
-- TOC entry 203 (class 1259 OID 16908)
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modelo (
    id_modelo integer NOT NULL,
    nombremo character varying(20),
    id_marca integer
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16906)
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
-- TOC entry 2537 (class 0 OID 0)
-- Dependencies: 202
-- Name: modelo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modelo_id_seq OWNED BY public.modelo.id_modelo;


--
-- TOC entry 205 (class 1259 OID 16914)
-- Name: modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modulo (
    id_modulo integer NOT NULL,
    nombremd character varying(50),
    descripcionmd character varying(50)
);


ALTER TABLE public.modulo OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16912)
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
-- TOC entry 2538 (class 0 OID 0)
-- Dependencies: 204
-- Name: modulo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modulo_id_seq OWNED BY public.modulo.id_modulo;


--
-- TOC entry 223 (class 1259 OID 83749)
-- Name: movimiento_bien; Type: TABLE; Schema: public; Owner: postgres
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
    id_mv_programa integer
);


ALTER TABLE public.movimiento_bien OWNER TO postgres;

--
-- TOC entry 2539 (class 0 OID 0)
-- Dependencies: 223
-- Name: COLUMN movimiento_bien.id_bien; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento_bien.id_bien IS 'referencia de la tabla bien';


--
-- TOC entry 2540 (class 0 OID 0)
-- Dependencies: 223
-- Name: COLUMN movimiento_bien.id_mv_modulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento_bien.id_mv_modulo IS 'referencia de tabla modulo';


--
-- TOC entry 222 (class 1259 OID 83747)
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
-- TOC entry 2541 (class 0 OID 0)
-- Dependencies: 222
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_bien_id_mov_bien_seq OWNED BY public.movimiento_bien.id_mov_bien;


--
-- TOC entry 241 (class 1259 OID 108937)
-- Name: opcion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opcion (
    id integer NOT NULL,
    codopc character varying(4) NOT NULL,
    nombre character varying(50)
);


ALTER TABLE public.opcion OWNER TO postgres;

--
-- TOC entry 2542 (class 0 OID 0)
-- Dependencies: 241
-- Name: TABLE opcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.opcion IS 'Opciones del Sistema';


--
-- TOC entry 240 (class 1259 OID 108935)
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
-- TOC entry 2544 (class 0 OID 0)
-- Dependencies: 240
-- Name: opcion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.opcion_id_seq OWNED BY public.opcion.id;


--
-- TOC entry 243 (class 1259 OID 108945)
-- Name: opcxperfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opcxperfil (
    id integer NOT NULL,
    codopc character varying(4),
    codperfil character(4)
);


ALTER TABLE public.opcxperfil OWNER TO postgres;

--
-- TOC entry 2545 (class 0 OID 0)
-- Dependencies: 243
-- Name: TABLE opcxperfil; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.opcxperfil IS 'Detalle de Opciones por Perfil del Sistema';


--
-- TOC entry 242 (class 1259 OID 108943)
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
-- TOC entry 2547 (class 0 OID 0)
-- Dependencies: 242
-- Name: opcxperfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.opcxperfil_id_seq OWNED BY public.opcxperfil.id;


--
-- TOC entry 213 (class 1259 OID 34154)
-- Name: perfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perfil (
    id_perfil integer NOT NULL,
    codperfil character varying(4) NOT NULL,
    descrip character varying(45) NOT NULL,
    estatus character(1) DEFAULT 'A'::bpchar
);


ALTER TABLE public.perfil OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 34152)
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
-- TOC entry 2548 (class 0 OID 0)
-- Dependencies: 212
-- Name: perfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.perfil_id_seq OWNED BY public.perfil.id_perfil;


--
-- TOC entry 217 (class 1259 OID 58947)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
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
    id_per_representante integer
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 67139)
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
-- TOC entry 2549 (class 0 OID 0)
-- Dependencies: 218
-- Name: persona_id_persona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persona_id_persona_seq OWNED BY public.persona.id_persona;


--
-- TOC entry 187 (class 1259 OID 16422)
-- Name: profesor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profesor (
    id integer NOT NULL,
    id_empleado integer,
    id_personapro integer
);


ALTER TABLE public.profesor OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 100231)
-- Name: profesor_catedra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profesor_catedra (
    id_prof_cate integer NOT NULL,
    id_profesor integer,
    id_catedra integer
);


ALTER TABLE public.profesor_catedra OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 100229)
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
-- TOC entry 2550 (class 0 OID 0)
-- Dependencies: 231
-- Name: profesor_catedra_id_prof_cate_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_catedra_id_prof_cate_seq OWNED BY public.profesor_catedra.id_prof_cate;


--
-- TOC entry 234 (class 1259 OID 100249)
-- Name: profesor_estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profesor_estudiante (
    id_profestudiante integer NOT NULL,
    id_profesor integer,
    id_estudiante integer
);


ALTER TABLE public.profesor_estudiante OWNER TO postgres;

--
-- TOC entry 2551 (class 0 OID 0)
-- Dependencies: 234
-- Name: TABLE profesor_estudiante; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.profesor_estudiante IS 'inner profesor estudiantes';


--
-- TOC entry 233 (class 1259 OID 100247)
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
-- TOC entry 2552 (class 0 OID 0)
-- Dependencies: 233
-- Name: profesor_estudiante_id_profestudiante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_estudiante_id_profestudiante_seq OWNED BY public.profesor_estudiante.id_profestudiante;


--
-- TOC entry 188 (class 1259 OID 16425)
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
-- TOC entry 2553 (class 0 OID 0)
-- Dependencies: 188
-- Name: profesor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_id_seq OWNED BY public.profesor.id;


--
-- TOC entry 209 (class 1259 OID 16927)
-- Name: programa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.programa (
    id_programa integer NOT NULL,
    nombrepro character varying(30),
    descripcionpro character varying(50),
    id_modulo integer
);


ALTER TABLE public.programa OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16925)
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
-- TOC entry 2554 (class 0 OID 0)
-- Dependencies: 208
-- Name: programa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.programa_id_seq OWNED BY public.programa.id_programa;


--
-- TOC entry 195 (class 1259 OID 16859)
-- Name: representante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.representante (
    id_representante integer NOT NULL,
    id_persona integer,
    id_estudiante integer
);


ALTER TABLE public.representante OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 75331)
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
-- TOC entry 2555 (class 0 OID 0)
-- Dependencies: 219
-- Name: representante_id_representante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.representante_id_representante_seq OWNED BY public.representante.id_representante;


--
-- TOC entry 225 (class 1259 OID 83755)
-- Name: tipo_movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_movimiento (
    id_tipo_move integer NOT NULL,
    nombre_move character varying(30)
);


ALTER TABLE public.tipo_movimiento OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 83753)
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
-- TOC entry 2556 (class 0 OID 0)
-- Dependencies: 224
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_movimiento_id_tipo_move_seq OWNED BY public.tipo_movimiento.id_tipo_move;


--
-- TOC entry 227 (class 1259 OID 83761)
-- Name: tipo_siniestro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_siniestro (
    id_tipos integer NOT NULL,
    nombrets character varying(30),
    descripcionts character varying(30)
);


ALTER TABLE public.tipo_siniestro OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 83759)
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
-- TOC entry 2557 (class 0 OID 0)
-- Dependencies: 226
-- Name: tipo_siniestro_id_tipos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_siniestro_id_tipos_seq OWNED BY public.tipo_siniestro.id_tipos;


--
-- TOC entry 196 (class 1259 OID 16870)
-- Name: tipobien; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipobien (
    id_tipob integer NOT NULL,
    nombretb character varying(20),
    descripcion character varying(50)
);


ALTER TABLE public.tipobien OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16873)
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
-- TOC entry 2559 (class 0 OID 0)
-- Dependencies: 197
-- Name: tipobien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipobien_id_seq OWNED BY public.tipobien.id_tipob;


--
-- TOC entry 198 (class 1259 OID 16888)
-- Name: tipoinstrumento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipoinstrumento (
    id_tipo_instrumento integer NOT NULL,
    nombreti character varying(20),
    descripcionti character varying(50)
);


ALTER TABLE public.tipoinstrumento OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16891)
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
-- TOC entry 2560 (class 0 OID 0)
-- Dependencies: 199
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipoinstrumento_id_seq OWNED BY public.tipoinstrumento.id_tipo_instrumento;


--
-- TOC entry 221 (class 1259 OID 75339)
-- Name: tipopersona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipopersona (
    id_tipopersona integer NOT NULL,
    nombretipoper character varying(20)
);


ALTER TABLE public.tipopersona OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 75337)
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
-- TOC entry 2561 (class 0 OID 0)
-- Dependencies: 220
-- Name: tipopersona_id_tipopersona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipopersona_id_tipopersona_seq OWNED BY public.tipopersona.id_tipopersona;


--
-- TOC entry 215 (class 1259 OID 34163)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    clave text NOT NULL,
    id_perfil integer,
    login character varying(30),
    status character varying(5),
    id_persona integer
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 34161)
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
-- TOC entry 2562 (class 0 OID 0)
-- Dependencies: 214
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 244 (class 1259 OID 117101)
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
    bn.id_tipob,
    tb.nombretb
   FROM (((public.bien bn
     JOIN public.marca mr ON (((bn.id_marca)::text = (mr.id_marca)::text)))
     JOIN public.tipobien tb ON (((bn.id_tipob)::text = (tb.id_tipob)::text)))
     JOIN public.modelo md ON (((bn.id_modelo)::text = (md.id_modelo)::text)))
  WHERE (bn.tipo_move = 5);


ALTER TABLE public.vbien OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 117165)
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
   FROM (((((((public.bien bn
     JOIN public.marca mr ON (((bn.id_marca)::text = (mr.id_marca)::text)))
     JOIN public.tipobien tb ON (((bn.id_tipob)::text = (tb.id_tipob)::text)))
     JOIN public.modelo md ON (((bn.id_modelo)::text = (md.id_modelo)::text)))
     JOIN public.movimiento_bien mv ON (((mv.id_bien)::text = (bn.id)::text)))
     JOIN public.tipo_movimiento tmv ON (((mv.id_tipo_movimiento)::text = (tmv.id_tipo_move)::text)))
     JOIN public.persona rs ON (((mv.reponsable)::text = (rs.id_persona)::text)))
     JOIN public.tipo_siniestro ts ON (((ts.id_tipos)::text = (mv.id_tipo_siniestro)::text)));


ALTER TABLE public.vbien_movimiento_bien OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 108419)
-- Name: vcatedra_estudiante; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vcatedra_estudiante AS
 SELECT eca.id_est,
    per.nombreper,
    ca.id_catedra,
    ca.nombrec
   FROM (((public.estudiante_catedra eca
     JOIN public.estudiante est ON (((eca.id_estudiante)::text = (est.id_estudiante)::text)))
     JOIN public.catedra ca ON (((eca.id_catedra)::text = (ca.id_catedra)::text)))
     JOIN public.persona per ON (((per.id_estudiante)::text = (est.id_estudiante)::text)));


ALTER TABLE public.vcatedra_estudiante OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 108409)
-- Name: vcatedra_profesor; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vcatedra_profesor AS
 SELECT pca.id_prof_cate,
    per.nombreper,
    ca.id_catedra,
    ca.nombrec
   FROM (((public.profesor_catedra pca
     JOIN public.profesor pro ON (((pca.id_profesor)::text = (pro.id)::text)))
     JOIN public.catedra ca ON (((pca.id_catedra)::text = (ca.id_catedra)::text)))
     JOIN public.persona per ON (((per.id_profesor)::text = (pro.id)::text)));


ALTER TABLE public.vcatedra_profesor OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 108501)
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
    es.id_representante
   FROM (((public.estudiante es
     JOIN public.persona pr ON (((es.id_persona)::text = (pr.id_persona)::text)))
     JOIN public.estudiante_catedra eca ON (((eca.id_estudiante)::text = (es.id_estudiante)::text)))
     JOIN public.catedra ca ON (((eca.id_catedra)::text = (ca.id_catedra)::text)));


ALTER TABLE public.vestudiante OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 117155)
-- Name: vmoviento_asignar; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vmoviento_asignar AS
 SELECT tmv.nombre_move,
    mv.fecha_move,
    mv.justificacion,
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
-- TOC entry 246 (class 1259 OID 117160)
-- Name: vmovientos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vmovientos AS
 SELECT tmv.nombre_move,
    mv.fecha_move,
    mv.justificacion,
    rs.nombreper,
    bn.nombreb,
    ts.nombrets,
    bn.id
   FROM ((((public.movimiento_bien mv
     JOIN public.tipo_movimiento tmv ON (((mv.id_tipo_movimiento)::text = (tmv.id_tipo_move)::text)))
     JOIN public.persona rs ON (((mv.reponsable)::text = (rs.id_persona)::text)))
     JOIN public.bien bn ON (((mv.id_bien)::text = (bn.id)::text)))
     JOIN public.tipo_siniestro ts ON (((ts.id_tipos)::text = (mv.id_tipo_siniestro)::text)));


ALTER TABLE public.vmovientos OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 108475)
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
    ca.nombrec
   FROM (((public.profesor pro
     JOIN public.persona pr ON (((pro.id_personapro)::text = (pr.id_persona)::text)))
     JOIN public.profesor_catedra pca ON (((pca.id_profesor)::text = (pro.id)::text)))
     JOIN public.catedra ca ON (((pca.id_catedra)::text = (ca.id_catedra)::text)));


ALTER TABLE public.vprofesor OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 100189)
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
-- TOC entry 235 (class 1259 OID 108405)
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
    us.login,
    us.clave,
    per.fnacimientoper,
    us.id_perfil,
    per.nacionper
   FROM (public.usuario us
     JOIN public.persona per ON (((us.id_persona)::text = (per.id_persona)::text)));


ALTER TABLE public.vrusuporperfil OWNER TO postgres;

--
-- TOC entry 2200 (class 2604 OID 16841)
-- Name: bien id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien ALTER COLUMN id SET DEFAULT nextval('public.bien_id_seq'::regclass);


--
-- TOC entry 2211 (class 2604 OID 17773)
-- Name: bitacora id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bitacora ALTER COLUMN id SET DEFAULT nextval('public.bitacora_id_seq'::regclass);


--
-- TOC entry 2201 (class 2604 OID 16850)
-- Name: cargo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo ALTER COLUMN id SET DEFAULT nextval('public.cargo_id_seq'::regclass);


--
-- TOC entry 2199 (class 2604 OID 16853)
-- Name: catedra id_catedra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catedra ALTER COLUMN id_catedra SET DEFAULT nextval('public.catedra_id_seq'::regclass);


--
-- TOC entry 2205 (class 2604 OID 16902)
-- Name: empleado id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id SET DEFAULT nextval('public.empleado_id_seq'::regclass);


--
-- TOC entry 2206 (class 2604 OID 34369)
-- Name: empleado id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id_usuario SET DEFAULT nextval('public.empleado_id_usuario_seq'::regclass);


--
-- TOC entry 2197 (class 2604 OID 16418)
-- Name: estudiante id_estudiante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante ALTER COLUMN id_estudiante SET DEFAULT nextval('public.estudiante_id_seq'::regclass);


--
-- TOC entry 2220 (class 2604 OID 100203)
-- Name: estudiante_catedra id_est; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra ALTER COLUMN id_est SET DEFAULT nextval('public.estudiante_catedra_id_est_seq'::regclass);


--
-- TOC entry 2209 (class 2604 OID 16924)
-- Name: marca id_marca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca ALTER COLUMN id_marca SET DEFAULT nextval('public.marca_id_seq'::regclass);


--
-- TOC entry 2207 (class 2604 OID 16911)
-- Name: modelo id_modelo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo ALTER COLUMN id_modelo SET DEFAULT nextval('public.modelo_id_seq'::regclass);


--
-- TOC entry 2208 (class 2604 OID 16917)
-- Name: modulo id_modulo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo ALTER COLUMN id_modulo SET DEFAULT nextval('public.modulo_id_seq'::regclass);


--
-- TOC entry 2217 (class 2604 OID 83752)
-- Name: movimiento_bien id_mov_bien; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien ALTER COLUMN id_mov_bien SET DEFAULT nextval('public.movimiento_bien_id_mov_bien_seq'::regclass);


--
-- TOC entry 2223 (class 2604 OID 108940)
-- Name: opcion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion ALTER COLUMN id SET DEFAULT nextval('public.opcion_id_seq'::regclass);


--
-- TOC entry 2224 (class 2604 OID 108948)
-- Name: opcxperfil id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil ALTER COLUMN id SET DEFAULT nextval('public.opcxperfil_id_seq'::regclass);


--
-- TOC entry 2212 (class 2604 OID 34157)
-- Name: perfil id_perfil; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil ALTER COLUMN id_perfil SET DEFAULT nextval('public.perfil_id_seq'::regclass);


--
-- TOC entry 2215 (class 2604 OID 67141)
-- Name: persona id_persona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona ALTER COLUMN id_persona SET DEFAULT nextval('public.persona_id_persona_seq'::regclass);


--
-- TOC entry 2198 (class 2604 OID 16427)
-- Name: profesor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor ALTER COLUMN id SET DEFAULT nextval('public.profesor_id_seq'::regclass);


--
-- TOC entry 2221 (class 2604 OID 100234)
-- Name: profesor_catedra id_prof_cate; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra ALTER COLUMN id_prof_cate SET DEFAULT nextval('public.profesor_catedra_id_prof_cate_seq'::regclass);


--
-- TOC entry 2222 (class 2604 OID 100252)
-- Name: profesor_estudiante id_profestudiante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante ALTER COLUMN id_profestudiante SET DEFAULT nextval('public.profesor_estudiante_id_profestudiante_seq'::regclass);


--
-- TOC entry 2210 (class 2604 OID 16930)
-- Name: programa id_programa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa ALTER COLUMN id_programa SET DEFAULT nextval('public.programa_id_seq'::regclass);


--
-- TOC entry 2202 (class 2604 OID 75333)
-- Name: representante id_representante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante ALTER COLUMN id_representante SET DEFAULT nextval('public.representante_id_representante_seq'::regclass);


--
-- TOC entry 2218 (class 2604 OID 83758)
-- Name: tipo_movimiento id_tipo_move; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento ALTER COLUMN id_tipo_move SET DEFAULT nextval('public.tipo_movimiento_id_tipo_move_seq'::regclass);


--
-- TOC entry 2219 (class 2604 OID 83764)
-- Name: tipo_siniestro id_tipos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_siniestro ALTER COLUMN id_tipos SET DEFAULT nextval('public.tipo_siniestro_id_tipos_seq'::regclass);


--
-- TOC entry 2203 (class 2604 OID 16875)
-- Name: tipobien id_tipob; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipobien ALTER COLUMN id_tipob SET DEFAULT nextval('public.tipobien_id_seq'::regclass);


--
-- TOC entry 2204 (class 2604 OID 16893)
-- Name: tipoinstrumento id_tipo_instrumento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipoinstrumento ALTER COLUMN id_tipo_instrumento SET DEFAULT nextval('public.tipoinstrumento_id_seq'::regclass);


--
-- TOC entry 2216 (class 2604 OID 75342)
-- Name: tipopersona id_tipopersona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona ALTER COLUMN id_tipopersona SET DEFAULT nextval('public.tipopersona_id_tipopersona_seq'::regclass);


--
-- TOC entry 2214 (class 2604 OID 34166)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 2470 (class 0 OID 16836)
-- Dependencies: 190
-- Data for Name: bien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('Mobiliario', 23, '', '', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('1', 29, '1', '2', '12', 3, 'cuatro', NULL, NULL, NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('123', 25, '3132', '3', '', 3, 'VIOLIN', 2, 2, NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('12313', 31, '333', '4', '00001', 7, 'MARACAS', 6, 1, NULL);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('23331', 32, '33', '4', '00013', 6, 'MESA', 6, 1, 3);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('12121', 33, '65', '5', '0010', 4, 'BIEN', 6, 1, 2);
INSERT INTO public.bien (serial, id, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo, tipo_move) VALUES ('39933', 34, '50', '50', '00050', 7, 'ESTUCHE', 8, 3, 2);


--
-- TOC entry 2563 (class 0 OID 0)
-- Dependencies: 191
-- Name: bien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bien_id_seq', 34, true);


--
-- TOC entry 2490 (class 0 OID 17768)
-- Dependencies: 210
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
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (248, 'CARGO', '(36,COORDINADOR,COORDINADOR,)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:05:42.226752', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (249, 'CARGO', '(37,PROFESOR,PROFESOR,)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:05:42.226752', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (250, 'CARGO', '(38,OBRERO,OBRERO,)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:05:42.226752', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (251, 'CARGO', '(39,ATRILERO,ALMACENISTA,)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:05:42.226752', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (252, 'CARGO', '(40,CONTADOR,CONTADOR,)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:05:42.226752', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (253, 'CATEDRA', '(6,VIOLIN,VIOLIN)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (254, 'CATEDRA', '(7,VIOLA,VIOLA)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (255, 'CATEDRA', '(8,VIOLONCELLO,VIOLONCELLO)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (256, 'CATEDRA', '(9,CONTRABAJO,CONTRABAJO)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (257, 'CATEDRA', '(10,FLAUTA,FLAUTA)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (258, 'CATEDRA', '(11,OBOE,OBOE)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (259, 'CATEDRA', '(12,TUBA,TUBA)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (260, 'CATEDRA', '(13,"HISTORIA DE LA MUSICA","HISTORIA DE LA MUSICA")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (261, 'CATEDRA', '(14,"LENGUAJE MUSICAL","LENGUAJE MUSICAL")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (262, 'CATEDRA', '(15,TROMPETA,TROMPETA)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:08:38.922369', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (263, 'MARCA', '(9,YAMAHA,YAMAHA)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (264, 'MARCA', '(10,IBANEZ,IBANEZ)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (265, 'MARCA', '(11,FENDER,FENDER)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (266, 'MARCA', '(12,SQUIER,SQUIER)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (267, 'MARCA', '(13,SCHECTER,SCHECTER)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (268, 'MARCA', '(14,NOVRE,NOVRE)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (269, 'MARCA', '(15,STENTOR,STENTOR)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (270, 'MARCA', '(16,LARRC,LARC)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (271, 'MARCA', '(17,BERBERIEN,BERBERIEN)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (272, 'MARCA', '(18,ASTOR,ASTOR)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:10:02.657639', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (273, 'MODELO', '(6,ROSEWOOD,3)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.137075', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (274, 'MODELO', '(7,"VIBE 60",4)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.168285', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (275, 'MODELO', '(8,"STUDIO 5C",5)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.199401', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (276, 'MODELO', '(9,BEGGINERS,1)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.230896', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (277, 'MODELO', '(10,MV012R,7)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.261955', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (278, 'MODELO', '(12,PRO2,9)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.324577', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (279, 'MODELO', '(13,"STUDENT I",10)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:11:19.355647', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (280, 'MODULO', '(5,"ORQUESTA PRE-INFANTIL M.R.C","PRE-INFANTIL MANUEL RODRIGUEZ CARDENAS")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:19:05.82761', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (281, 'MODULO', '(6,"CONSERVATORIO B.E.M","CONSERVATORIO BLANCA ESTRELLA DE MESCOLI")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:19:05.82761', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (282, 'MODULO', '(7,"LAS PIEDRAS","MODULO LAS PIEDRAS")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:19:05.82761', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (283, 'MARCA', '(19,YAMAHA,YAMAHA)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (284, 'MARCA', '(20,IBANEZ,IBANEZ)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (285, 'MARCA', '(21,FENDER,FENDER)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (286, 'MARCA', '(22,SQUIER,SQUIER)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (287, 'MARCA', '(23,SCHECTER,SCHECTER)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (288, 'MARCA', '(24,NOVRE,NOVRE)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (289, 'MARCA', '(25,STENTOR,STENTOR)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (290, 'MARCA', '(26,LARRC,LARC)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (291, 'MARCA', '(27,BERBERIEN,BERBERIEN)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (292, 'MARCA', '(28,ASTOR,ASTOR)', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:20:17.531098', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (295, 'TIPOBIEN', '(8,MOBILIARIO,"BIEN MUEBLE")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:30:47.647316', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (296, 'TIPOINSTRUMENTO', '(6,CUERDA,"INSTRUMENTOS DE CUERDAS")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:32:57.283765', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (297, 'TIPOINSTRUMENTO', '(7,VIENTO,"INSTRUMENTOS DE VIENTO")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:32:57.283765', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (298, 'TIPOINSTRUMENTO', '(8,PERCUSION,"INSTRUMENTOS DE PERCUSION")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:32:57.283765', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (299, 'TIPOINSTRUMENTO', '(9,ELECTRICOS,"INSTRUMENTOS ELECTRICOS")', 'postgres', '2018-10-10', NULL, 'REGISTRAR', NULL, '16:32:57.283765', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (308, 'CARGO', '(31,EJEPLOA,EJEPLO,)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', NULL, '18:32:09.813577', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (309, 'CARGO', '(34,SSSS,QQQQQ,)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', NULL, '18:32:56.368264', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (310, 'CARGO', '(26,EJEMPLO,DDDDD,)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', NULL, '18:33:04.247121', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (311, 'CARGO', '(23,CARGONOM,"SEGIO ES DESCRIPCION",)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', NULL, '18:34:32.163139', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (312, 'CARGO', '(28,CARGODOS,DESCRIPDOS,)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', NULL, '18:34:48.343636', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (313, 'CARGO', '(27,CARGOUNO,DESCRIPUNO,)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', NULL, '18:34:58.310825', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (314, 'MODULO', '(3,DEEE,OTRO)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMIMO LA MARCA DEEE OTRO', '18:44:29.341096', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (315, 'MODULO', '(4,EQEQEQWE,ASDADADASD)', 'postgres', '2018-10-10', NULL, 'ELIMINAR', 'EL USUARIO ADMIN ELIMIMO LA MARCA EQEQEQWE ASDADADASD', '19:24:09.399996', 4);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (316, 'MARCA', '(6,MARCAE,DESCRIPCION)', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '14:50:58.062841', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (317, 'MARCA', '(2,NIKE,"ZAPATOS NIKE")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '14:51:05.687011', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (318, 'MARCA', '(8,ALTERNATIVO,"SIN MARCA")', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '14:51:39.050399', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (319, 'MODELO', '(2,nombreM,2)', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '14:55:55.410366', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (320, 'PROGRAMA', '(4,"PROGRAMA NOMBRE",DESCRIP,1)', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '14:57:17.64265', NULL);
INSERT INTO public.bitacora (id, entidad, sentencia, usuario, fecha, formulario, accion, descripcion, hora, id_empleado) VALUES (321, 'PROGRAMA', '(6,DDDD,AAAA,2)', 'postgres', '2018-10-11', NULL, 'ELIMINAR', NULL, '14:57:27.795551', NULL);


--
-- TOC entry 2564 (class 0 OID 0)
-- Dependencies: 211
-- Name: bitacora_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bitacora_id_seq', 321, true);


--
-- TOC entry 2473 (class 0 OID 16847)
-- Dependencies: 193
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (1, 'ADMINISTRADOR', 'REGISTRA ESTUDIANTES', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (22, 'SECRETARIA', 'REGISTRAR ESTUDIANTE', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (29, 'DIRECTOR', 'ADMINISTRA EL PLANTEL', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (30, 'DOCENTE', 'PREPARA A LOS ESTUDIANTES', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (36, 'COORDINADOR', 'COORDINADOR', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (37, 'PROFESOR', 'PROFESOR', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (38, 'OBRERO', 'OBRERO', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (39, 'ATRILERO', 'ALMACENISTA', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (40, 'CONTADOR', 'CONTADOR', NULL);


--
-- TOC entry 2565 (class 0 OID 0)
-- Dependencies: 192
-- Name: cargo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cargo_id_seq', 40, true);


--
-- TOC entry 2469 (class 0 OID 16827)
-- Dependencies: 189
-- Data for Name: catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (4, 'CATEDRA', 'DESCRICION');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (6, 'VIOLIN', 'VIOLIN');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (7, 'VIOLA', 'VIOLA');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (8, 'VIOLONCELLO', 'VIOLONCELLO');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (9, 'CONTRABAJO', 'CONTRABAJO');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (10, 'FLAUTA', 'FLAUTA');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (11, 'OBOE', 'OBOE');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (12, 'TUBA', 'TUBA');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (13, 'HISTORIA DE LA MUSICA', 'HISTORIA DE LA MUSICA');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (14, 'LENGUAJE MUSICAL', 'LENGUAJE MUSICAL');
INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (15, 'TROMPETA', 'TROMPETA');


--
-- TOC entry 2566 (class 0 OID 0)
-- Dependencies: 194
-- Name: catedra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.catedra_id_seq', 15, true);


--
-- TOC entry 2480 (class 0 OID 16897)
-- Dependencies: 200
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.empleado (id, id_usuario, id_cargo, id_persona) VALUES (6, 4, NULL, 2);


--
-- TOC entry 2567 (class 0 OID 0)
-- Dependencies: 201
-- Name: empleado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_seq', 6, true);


--
-- TOC entry 2568 (class 0 OID 0)
-- Dependencies: 216
-- Name: empleado_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_usuario_seq', 2, true);


--
-- TOC entry 2465 (class 0 OID 16413)
-- Dependencies: 185
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estudiante (id_estudiante, id_representante, id_catedra, id_persona) VALUES (23, 1, 4, 2);


--
-- TOC entry 2509 (class 0 OID 100200)
-- Dependencies: 230
-- Data for Name: estudiante_catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estudiante_catedra (id_est, id_estudiante, id_catedra) VALUES (1, 23, 4);


--
-- TOC entry 2569 (class 0 OID 0)
-- Dependencies: 229
-- Name: estudiante_catedra_id_est_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estudiante_catedra_id_est_seq', 1, true);


--
-- TOC entry 2570 (class 0 OID 0)
-- Dependencies: 186
-- Name: estudiante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estudiante_id_seq', 23, true);


--
-- TOC entry 2487 (class 0 OID 16921)
-- Dependencies: 207
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (7, 'SIN MARCA', 'ARTESANALES');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (9, 'YAMAHA', 'YAMAHA');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (10, 'IBANEZ', 'IBANEZ');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (11, 'FENDER', 'FENDER');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (12, 'SQUIER', 'SQUIER');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (13, 'SCHECTER', 'SCHECTER');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (14, 'NOVRE', 'NOVRE');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (15, 'STENTOR', 'STENTOR');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (16, 'LARRC', 'LARC');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (17, 'BERBERIEN', 'BERBERIEN');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (18, 'ASTOR', 'ASTOR');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (19, 'YAMAHA', 'YAMAHA');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (20, 'IBANEZ', 'IBANEZ');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (21, 'FENDER', 'FENDER');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (22, 'SQUIER', 'SQUIER');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (23, 'SCHECTER', 'SCHECTER');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (24, 'NOVRE', 'NOVRE');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (25, 'STENTOR', 'STENTOR');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (26, 'LARRC', 'LARC');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (27, 'BERBERIEN', 'BERBERIEN');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (28, 'ASTOR', 'ASTOR');


--
-- TOC entry 2571 (class 0 OID 0)
-- Dependencies: 206
-- Name: marca_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marca_id_seq', 28, true);


--
-- TOC entry 2483 (class 0 OID 16908)
-- Dependencies: 203
-- Data for Name: modelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (1, 'modelo', 6);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (3, 'ALTERNATIVO', 8);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (6, 'ROSEWOOD', 3);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (7, 'VIBE 60', 4);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (8, 'STUDIO 5C', 5);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (9, 'BEGGINERS', 1);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (10, 'MV012R', 7);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (12, 'PRO2', 9);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (13, 'STUDENT I', 10);


--
-- TOC entry 2572 (class 0 OID 0)
-- Dependencies: 202
-- Name: modelo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modelo_id_seq', 28, true);


--
-- TOC entry 2485 (class 0 OID 16914)
-- Dependencies: 205
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd) VALUES (1, 'DESC', 'MODULO');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd) VALUES (2, 'DDDDD', 'MODULO');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd) VALUES (5, 'ORQUESTA PRE-INFANTIL M.R.C', 'PRE-INFANTIL MANUEL RODRIGUEZ CARDENAS');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd) VALUES (6, 'CONSERVATORIO B.E.M', 'CONSERVATORIO BLANCA ESTRELLA DE MESCOLI');
INSERT INTO public.modulo (id_modulo, nombremd, descripcionmd) VALUES (7, 'LAS PIEDRAS', 'MODULO LAS PIEDRAS');


--
-- TOC entry 2573 (class 0 OID 0)
-- Dependencies: 204
-- Name: modulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modulo_id_seq', 7, true);


--
-- TOC entry 2503 (class 0 OID 83749)
-- Dependencies: 223
-- Data for Name: movimiento_bien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa) VALUES (2, '2018-08-10', 'JUSTIFICADO', 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa) VALUES (11, '2018-10-08', 'POR QUE ESTA HECTOR', 2, 32, 3, 5, NULL, NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa) VALUES (13, '2018-10-08', 'JUSTIFICADO BIEN', 2, 33, 2, 6, 1, NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, fecha_move, justificacion, reponsable, id_bien, id_tipo_movimiento, id_tipo_siniestro, id_mv_modulo, id_mv_programa) VALUES (14, '2018-10-09', 'APUESTO ', 2, 34, 2, 6, 3, 5);


--
-- TOC entry 2574 (class 0 OID 0)
-- Dependencies: 222
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_bien_id_mov_bien_seq', 14, true);


--
-- TOC entry 2515 (class 0 OID 108937)
-- Dependencies: 241
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
INSERT INTO public.opcion (id, codopc, nombre) VALUES (18, '16', 'MOVIMIENTO BIEN');
INSERT INTO public.opcion (id, codopc, nombre) VALUES (20, '18', 'BITACORA');


--
-- TOC entry 2575 (class 0 OID 0)
-- Dependencies: 240
-- Name: opcion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcion_id_seq', 20, true);


--
-- TOC entry 2517 (class 0 OID 108945)
-- Dependencies: 243
-- Data for Name: opcxperfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (5, '1', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (8, '17', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (10, '2', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (13, '3', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (14, '4', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (6, '5', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (15, '6', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (16, '7', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (17, '8', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (18, '9', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (19, '10', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (20, '11', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (21, '12', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (22, '13', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (23, '14', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (24, '15', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (27, '16', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (28, '17', '6   ');
INSERT INTO public.opcxperfil (id, codopc, codperfil) VALUES (29, '18', '6   ');


--
-- TOC entry 2576 (class 0 OID 0)
-- Dependencies: 242
-- Name: opcxperfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcxperfil_id_seq', 29, true);


--
-- TOC entry 2493 (class 0 OID 34154)
-- Dependencies: 213
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.perfil (id_perfil, codperfil, descrip, estatus) VALUES (6, '1', 'ADMINISTRADOR', 's');


--
-- TOC entry 2577 (class 0 OID 0)
-- Dependencies: 212
-- Name: perfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.perfil_id_seq', 3, true);


--
-- TOC entry 2497 (class 0 OID 58947)
-- Dependencies: 217
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante) VALUES (3, 'GOCHO', 'TORRES', '6601992', '04125083262', 'M', 'YARITAGUA', '1996-08-05', 4, 'V', 3, NULL, NULL);
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante) VALUES (1, 'CESAR', 'MOLINA', '25646854', '04125083262', 'M', 'YARITAGUA', '1996-08-05', 5, 'V', NULL, 23, NULL);
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante) VALUES (2, 'AUGUSTO', 'MOLINA', '6844714', '04125083262', 'M', 'YARITAGUA LA PASTORA', '1996-08-05', 2, 'V', NULL, NULL, NULL);
INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper, nacionper, id_profesor, id_estudiante, id_per_representante) VALUES (4, 'JOSE', 'GOMEZ', '6601992', '04265028463', 'M', 'SAN JOSE', '1996-06-04', 2, 'V', NULL, NULL, 1);


--
-- TOC entry 2578 (class 0 OID 0)
-- Dependencies: 218
-- Name: persona_id_persona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persona_id_persona_seq', 4, true);


--
-- TOC entry 2467 (class 0 OID 16422)
-- Dependencies: 187
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.profesor (id, id_empleado, id_personapro) VALUES (3, NULL, 2);


--
-- TOC entry 2511 (class 0 OID 100231)
-- Dependencies: 232
-- Data for Name: profesor_catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.profesor_catedra (id_prof_cate, id_profesor, id_catedra) VALUES (1, 3, 4);


--
-- TOC entry 2579 (class 0 OID 0)
-- Dependencies: 231
-- Name: profesor_catedra_id_prof_cate_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_catedra_id_prof_cate_seq', 1, true);


--
-- TOC entry 2513 (class 0 OID 100249)
-- Dependencies: 234
-- Data for Name: profesor_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2580 (class 0 OID 0)
-- Dependencies: 233
-- Name: profesor_estudiante_id_profestudiante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_estudiante_id_profestudiante_seq', 1, false);


--
-- TOC entry 2581 (class 0 OID 0)
-- Dependencies: 188
-- Name: profesor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_id_seq', 3, true);


--
-- TOC entry 2489 (class 0 OID 16927)
-- Dependencies: 209
-- Data for Name: programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.programa (id_programa, nombrepro, descripcionpro, id_modulo) VALUES (5, 'PROGRA ESPACIAL', 'DESDE MI CASA', 1);


--
-- TOC entry 2582 (class 0 OID 0)
-- Dependencies: 208
-- Name: programa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.programa_id_seq', 16, true);


--
-- TOC entry 2475 (class 0 OID 16859)
-- Dependencies: 195
-- Data for Name: representante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.representante (id_representante, id_persona, id_estudiante) VALUES (1, 1, 23);


--
-- TOC entry 2583 (class 0 OID 0)
-- Dependencies: 219
-- Name: representante_id_representante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.representante_id_representante_seq', 1, true);


--
-- TOC entry 2505 (class 0 OID 83755)
-- Dependencies: 225
-- Data for Name: tipo_movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (2, 'ASIGNAR');
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (3, 'DESINCORPORAR');
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (1, 'REPARAR');
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (4, 'PRESTAR');
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (5, 'DISPONIBLE');


--
-- TOC entry 2584 (class 0 OID 0)
-- Dependencies: 224
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_movimiento_id_tipo_move_seq', 5, true);


--
-- TOC entry 2507 (class 0 OID 83761)
-- Dependencies: 227
-- Data for Name: tipo_siniestro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts) VALUES (2, 'EXTRAVIADO', 'NO TIENE PRESENCIA');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts) VALUES (4, 'DAÑADO', 'PROBLEMAS FISICOS');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts) VALUES (3, 'QUEMADO', 'PROBLEMAS POR FUEGO');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts) VALUES (1, 'ROBADO', 'DESCRIPCION');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts) VALUES (5, 'REPARAR', 'POR CAMBIO DE PIESAS');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets, descripcionts) VALUES (6, 'SIN TIPO SINIESTRO', 'VALOR NULO O POR DEFECTO');


--
-- TOC entry 2585 (class 0 OID 0)
-- Dependencies: 226
-- Name: tipo_siniestro_id_tipos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_siniestro_id_tipos_seq', 12, true);


--
-- TOC entry 2476 (class 0 OID 16870)
-- Dependencies: 196
-- Data for Name: tipobien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (3, 'CUERDAS', 'INSTRUMENTO DE HILOS');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (4, 'MADERA', 'DESPCAD');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (5, 'DE AIRE', 'DEASDASDASASDA');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (6, 'MUEBLES', 'TODO LO QUE INCLUYA M');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (7, 'INSTRUMENTALES', 'TODO SOBRE INSTRUMENT');
INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (8, 'MOBILIARIO', 'BIEN MUEBLE');


--
-- TOC entry 2586 (class 0 OID 0)
-- Dependencies: 197
-- Name: tipobien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipobien_id_seq', 8, true);


--
-- TOC entry 2478 (class 0 OID 16888)
-- Dependencies: 198
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
-- TOC entry 2587 (class 0 OID 0)
-- Dependencies: 199
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipoinstrumento_id_seq', 9, true);


--
-- TOC entry 2501 (class 0 OID 75339)
-- Dependencies: 221
-- Data for Name: tipopersona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (1, 'EMPLEADO');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (2, 'ESTUDIANTE');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (3, 'PROFESOR');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (4, 'USUARIO');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (5, 'REPRESENTANTE');


--
-- TOC entry 2588 (class 0 OID 0)
-- Dependencies: 220
-- Name: tipopersona_id_tipopersona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipopersona_id_tipopersona_seq', 5, true);


--
-- TOC entry 2495 (class 0 OID 34163)
-- Dependencies: 215
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, clave, id_perfil, login, status, id_persona) VALUES (4, '123', 6, 'ADMIN', 'TRUE', 3);


--
-- TOC entry 2589 (class 0 OID 0)
-- Dependencies: 214
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 4, true);


--
-- TOC entry 2234 (class 2606 OID 91951)
-- Name: bien bien_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_id_key UNIQUE (id);


--
-- TOC entry 2236 (class 2606 OID 117094)
-- Name: bien bien_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_pkey PRIMARY KEY (id);


--
-- TOC entry 2238 (class 2606 OID 108892)
-- Name: cargo cargo_nombrecar_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_nombrecar_key UNIQUE (nombrecar);


--
-- TOC entry 2240 (class 2606 OID 34219)
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);


--
-- TOC entry 2232 (class 2606 OID 50720)
-- Name: catedra catedra_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catedra
    ADD CONSTRAINT catedra_id_key UNIQUE (id_catedra);


--
-- TOC entry 2292 (class 2606 OID 108942)
-- Name: opcion codopc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT codopc PRIMARY KEY (codopc);


--
-- TOC entry 2262 (class 2606 OID 34160)
-- Name: perfil codperfil; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT codperfil PRIMARY KEY (codperfil);


--
-- TOC entry 2248 (class 2606 OID 100266)
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id);


--
-- TOC entry 2286 (class 2606 OID 100205)
-- Name: estudiante_catedra estudiante_catedra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra
    ADD CONSTRAINT estudiante_catedra_pkey PRIMARY KEY (id_est);


--
-- TOC entry 2226 (class 2606 OID 100139)
-- Name: estudiante estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id_estudiante);


--
-- TOC entry 2294 (class 2606 OID 108950)
-- Name: opcxperfil idopcper; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT idopcper PRIMARY KEY (id);


--
-- TOC entry 2250 (class 2606 OID 17781)
-- Name: modelo modelo_id_marca_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_id_marca_key UNIQUE (id_marca);


--
-- TOC entry 2252 (class 2606 OID 100221)
-- Name: modulo modulo_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_id_key UNIQUE (id_modulo);


--
-- TOC entry 2254 (class 2606 OID 117109)
-- Name: modulo modulo_id_modulo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_id_modulo_key UNIQUE (id_modulo);


--
-- TOC entry 2256 (class 2606 OID 100219)
-- Name: modulo modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_pkey PRIMARY KEY (id_modulo);


--
-- TOC entry 2276 (class 2606 OID 83770)
-- Name: movimiento_bien movimiento_bien_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_pkey PRIMARY KEY (id_mov_bien);


--
-- TOC entry 2264 (class 2606 OID 34290)
-- Name: perfil perfil_codperfil_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_codperfil_key UNIQUE (codperfil);


--
-- TOC entry 2266 (class 2606 OID 34312)
-- Name: perfil perfil_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_id_key UNIQUE (id_perfil);


--
-- TOC entry 2270 (class 2606 OID 75344)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2288 (class 2606 OID 100236)
-- Name: profesor_catedra profesor_catedra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra
    ADD CONSTRAINT profesor_catedra_pkey PRIMARY KEY (id_prof_cate);


--
-- TOC entry 2290 (class 2606 OID 100254)
-- Name: profesor_estudiante profesor_estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante
    ADD CONSTRAINT profesor_estudiante_pkey PRIMARY KEY (id_profestudiante);


--
-- TOC entry 2228 (class 2606 OID 50713)
-- Name: profesor profesor_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_key UNIQUE (id);


--
-- TOC entry 2230 (class 2606 OID 108460)
-- Name: profesor profesor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_pkey PRIMARY KEY (id);


--
-- TOC entry 2258 (class 2606 OID 117111)
-- Name: programa programa_id_programa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_id_programa_key UNIQUE (id_programa);


--
-- TOC entry 2260 (class 2606 OID 100223)
-- Name: programa programa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (id_programa);


--
-- TOC entry 2242 (class 2606 OID 100137)
-- Name: representante representante_id_representante_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_representante_key UNIQUE (id_representante);


--
-- TOC entry 2244 (class 2606 OID 100135)
-- Name: representante representante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_pkey PRIMARY KEY (id_representante);


--
-- TOC entry 2278 (class 2606 OID 83768)
-- Name: tipo_movimiento tipo_movimiento_id_tipo_move_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_id_tipo_move_key UNIQUE (id_tipo_move);


--
-- TOC entry 2280 (class 2606 OID 83766)
-- Name: tipo_movimiento tipo_movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_pkey PRIMARY KEY (id_tipo_move);


--
-- TOC entry 2282 (class 2606 OID 108433)
-- Name: tipo_siniestro tipo_siniestro_id_tipos_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_siniestro
    ADD CONSTRAINT tipo_siniestro_id_tipos_key UNIQUE (id_tipos);


--
-- TOC entry 2284 (class 2606 OID 108431)
-- Name: tipo_siniestro tipo_siniestro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_siniestro
    ADD CONSTRAINT tipo_siniestro_pkey PRIMARY KEY (id_tipos);


--
-- TOC entry 2246 (class 2606 OID 58933)
-- Name: tipobien tipobien_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipobien
    ADD CONSTRAINT tipobien_id_key UNIQUE (id_tipob);


--
-- TOC entry 2272 (class 2606 OID 75348)
-- Name: tipopersona tipopersona_id_tipopersona_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona
    ADD CONSTRAINT tipopersona_id_tipopersona_key UNIQUE (id_tipopersona);


--
-- TOC entry 2274 (class 2606 OID 75346)
-- Name: tipopersona tipopersona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona
    ADD CONSTRAINT tipopersona_pkey PRIMARY KEY (id_tipopersona);


--
-- TOC entry 2268 (class 2606 OID 34360)
-- Name: usuario usuario_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_key UNIQUE (id);


--
-- TOC entry 2334 (class 2620 OID 42479)
-- Name: marca tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.marca FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2325 (class 2620 OID 42480)
-- Name: catedra tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.catedra FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2331 (class 2620 OID 42481)
-- Name: empleado tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.empleado FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2323 (class 2620 OID 42482)
-- Name: estudiante tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.estudiante FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2332 (class 2620 OID 42484)
-- Name: modelo tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.modelo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2333 (class 2620 OID 42485)
-- Name: modulo tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.modulo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2336 (class 2620 OID 42486)
-- Name: perfil tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.perfil FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2335 (class 2620 OID 42488)
-- Name: programa tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.programa FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2328 (class 2620 OID 42489)
-- Name: representante tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.representante FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2329 (class 2620 OID 42490)
-- Name: tipobien tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.tipobien FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2330 (class 2620 OID 42491)
-- Name: tipoinstrumento tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.tipoinstrumento FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2327 (class 2620 OID 42493)
-- Name: cargo tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.cargo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2326 (class 2620 OID 42494)
-- Name: bien tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.bien FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2337 (class 2620 OID 42520)
-- Name: usuario tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE ON public.usuario FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2324 (class 2620 OID 50746)
-- Name: profesor tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.profesor FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2299 (class 2606 OID 58934)
-- Name: bien bien_id_tipob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_id_tipob_fkey FOREIGN KEY (id_tipob) REFERENCES public.tipobien(id_tipob);


--
-- TOC entry 2300 (class 2606 OID 117095)
-- Name: bien bien_tipo_move_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_tipo_move_fkey FOREIGN KEY (tipo_move) REFERENCES public.tipo_movimiento(id_tipo_move);


--
-- TOC entry 2322 (class 2606 OID 108951)
-- Name: opcxperfil codopc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT codopc FOREIGN KEY (codopc) REFERENCES public.opcion(codopc);


--
-- TOC entry 2304 (class 2606 OID 100267)
-- Name: empleado empleado_id_cargo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_cargo_fkey FOREIGN KEY (id_cargo) REFERENCES public.cargo(id);


--
-- TOC entry 2303 (class 2606 OID 34407)
-- Name: empleado empleado_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- TOC entry 2317 (class 2606 OID 100211)
-- Name: estudiante_catedra estudiante_catedra_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra
    ADD CONSTRAINT estudiante_catedra_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2316 (class 2606 OID 100206)
-- Name: estudiante_catedra estudiante_catedra_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_catedra
    ADD CONSTRAINT estudiante_catedra_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2296 (class 2606 OID 100174)
-- Name: estudiante estudiante_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2297 (class 2606 OID 100179)
-- Name: estudiante estudiante_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);


--
-- TOC entry 2295 (class 2606 OID 100140)
-- Name: estudiante estudiante_id_representante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_id_representante_fkey FOREIGN KEY (id_representante) REFERENCES public.representante(id_representante);


--
-- TOC entry 2312 (class 2606 OID 91952)
-- Name: movimiento_bien movimiento_bien_id_bien_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_bien_fkey FOREIGN KEY (id_bien) REFERENCES public.bien(id);


--
-- TOC entry 2315 (class 2606 OID 117112)
-- Name: movimiento_bien movimiento_bien_id_mv_programa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_mv_programa_fkey FOREIGN KEY (id_mv_programa) REFERENCES public.programa(id_programa);


--
-- TOC entry 2313 (class 2606 OID 108434)
-- Name: movimiento_bien movimiento_bien_id_tipo_movimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_tipo_movimiento_fkey FOREIGN KEY (id_tipo_movimiento) REFERENCES public.tipo_siniestro(id_tipos);


--
-- TOC entry 2314 (class 2606 OID 108444)
-- Name: movimiento_bien movimiento_bien_id_tipo_siniestro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_tipo_siniestro_fkey FOREIGN KEY (id_tipo_siniestro) REFERENCES public.tipo_siniestro(id_tipos);


--
-- TOC entry 2310 (class 2606 OID 108414)
-- Name: persona persona_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2311 (class 2606 OID 117149)
-- Name: persona persona_id_per_representante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_per_representante_fkey FOREIGN KEY (id_per_representante) REFERENCES public.representante(id_representante);


--
-- TOC entry 2309 (class 2606 OID 108395)
-- Name: persona persona_id_profesor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_id_profesor_fkey FOREIGN KEY (id_profesor) REFERENCES public.profesor(id);


--
-- TOC entry 2308 (class 2606 OID 75349)
-- Name: persona persona_tipoper_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_tipoper_fkey FOREIGN KEY (tipoper) REFERENCES public.tipopersona(id_tipopersona);


--
-- TOC entry 2318 (class 2606 OID 100237)
-- Name: profesor_catedra profesor_catedra_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra
    ADD CONSTRAINT profesor_catedra_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2319 (class 2606 OID 100242)
-- Name: profesor_catedra profesor_catedra_id_profesor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_catedra
    ADD CONSTRAINT profesor_catedra_id_profesor_fkey FOREIGN KEY (id_profesor) REFERENCES public.profesor(id);


--
-- TOC entry 2320 (class 2606 OID 100255)
-- Name: profesor_estudiante profesor_estudiante_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante
    ADD CONSTRAINT profesor_estudiante_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2321 (class 2606 OID 100260)
-- Name: profesor_estudiante profesor_estudiante_id_profesor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor_estudiante
    ADD CONSTRAINT profesor_estudiante_id_profesor_fkey FOREIGN KEY (id_profesor) REFERENCES public.profesor(id);


--
-- TOC entry 2298 (class 2606 OID 108461)
-- Name: profesor profesor_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_persona_fkey FOREIGN KEY (id_personapro) REFERENCES public.persona(id_persona);


--
-- TOC entry 2305 (class 2606 OID 100224)
-- Name: programa programa_id_modulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_id_modulo_fkey FOREIGN KEY (id_modulo) REFERENCES public.modulo(id_modulo);


--
-- TOC entry 2302 (class 2606 OID 100160)
-- Name: representante representante_id_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante);


--
-- TOC entry 2301 (class 2606 OID 100145)
-- Name: representante representante_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);


--
-- TOC entry 2306 (class 2606 OID 34330)
-- Name: usuario usuario_id_perfil_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES public.perfil(id_perfil);


--
-- TOC entry 2307 (class 2606 OID 108390)
-- Name: usuario usuario_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);


--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE modelo; Type: ACL; Schema: public; Owner: postgres
--

GRANT UPDATE ON TABLE public.modelo TO PUBLIC;


--
-- TOC entry 2543 (class 0 OID 0)
-- Dependencies: 241
-- Name: TABLE opcion; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.opcion TO PUBLIC;


--
-- TOC entry 2546 (class 0 OID 0)
-- Dependencies: 243
-- Name: TABLE opcxperfil; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.opcxperfil TO PUBLIC;


--
-- TOC entry 2558 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE tipobien; Type: ACL; Schema: public; Owner: postgres
--

GRANT UPDATE ON TABLE public.tipobien TO PUBLIC;


-- Completed on 2018-10-17 12:06:16

--
-- PostgreSQL database dump complete
--

