--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

-- Started on 2018-08-30 21:08:14

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
-- TOC entry 2409 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 247 (class 1255 OID 42478)
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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 190 (class 1259 OID 16836)
-- Name: bien; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bien (
    modelo character varying(20),
    serial character varying(20),
    id integer NOT NULL,
    marca character varying(20),
    medida character varying(10),
    cantidad character varying(10),
    ninventario character varying(19),
    id_tipob integer,
    nombreb character varying(40),
    id_marca integer,
    id_modelo integer
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
-- TOC entry 2410 (class 0 OID 0)
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
-- TOC entry 2411 (class 0 OID 0)
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
-- TOC entry 2412 (class 0 OID 0)
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
-- TOC entry 2413 (class 0 OID 0)
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
    nombre character varying(20),
    apellido character varying(20),
    cedula character varying(8),
    direccion character varying(100),
    telefono character varying(12),
    sexo character varying(10),
    cargo character varying(50),
    fechanac date,
    usuario character varying(10),
    contrasena character varying(30),
    nacion character(10),
    id_usuario integer NOT NULL
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
-- TOC entry 2414 (class 0 OID 0)
-- Dependencies: 201
-- Name: empleado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_seq OWNED BY public.empleado.id;


--
-- TOC entry 220 (class 1259 OID 34367)
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
-- TOC entry 2415 (class 0 OID 0)
-- Dependencies: 220
-- Name: empleado_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_usuario_seq OWNED BY public.empleado.id_usuario;


--
-- TOC entry 185 (class 1259 OID 16413)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudiante (
    id integer NOT NULL,
    cedula character varying(9),
    nombre character varying(20),
    direccion character varying(100),
    telefono character varying(12),
    catedra character varying(30),
    sexo character varying(10),
    apellido character varying(20),
    fnacimiento date,
    nacion character varying(1)
);


ALTER TABLE public.estudiante OWNER TO postgres;

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
-- TOC entry 2416 (class 0 OID 0)
-- Dependencies: 186
-- Name: estudiante_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estudiante_id_seq OWNED BY public.estudiante.id;


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
-- TOC entry 2417 (class 0 OID 0)
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
-- TOC entry 2418 (class 0 OID 0)
-- Dependencies: 202
-- Name: modelo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modelo_id_seq OWNED BY public.modelo.id_modelo;


--
-- TOC entry 205 (class 1259 OID 16914)
-- Name: modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modulo (
    id integer NOT NULL,
    nombre character varying(20),
    descripcion character varying(30)
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
-- TOC entry 2419 (class 0 OID 0)
-- Dependencies: 204
-- Name: modulo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modulo_id_seq OWNED BY public.modulo.id;


--
-- TOC entry 229 (class 1259 OID 83749)
-- Name: movimiento_bien; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimiento_bien (
    id_mov_bien integer NOT NULL,
    tipo_mov integer,
    fecha_move date,
    justificacion character varying(50),
    reponsable integer,
    tipo_siniestro character varying(20),
    id_bien integer
);


ALTER TABLE public.movimiento_bien OWNER TO postgres;

--
-- TOC entry 2420 (class 0 OID 0)
-- Dependencies: 229
-- Name: COLUMN movimiento_bien.id_bien; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento_bien.id_bien IS 'referencia de la tabla bien';


--
-- TOC entry 228 (class 1259 OID 83747)
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
-- TOC entry 2421 (class 0 OID 0)
-- Dependencies: 228
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_bien_id_mov_bien_seq OWNED BY public.movimiento_bien.id_mov_bien;


--
-- TOC entry 217 (class 1259 OID 34188)
-- Name: opcion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opcion (
    id integer NOT NULL,
    nombre character varying(50)
);


ALTER TABLE public.opcion OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 34186)
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
-- TOC entry 2422 (class 0 OID 0)
-- Dependencies: 216
-- Name: opcion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.opcion_id_seq OWNED BY public.opcion.id;


--
-- TOC entry 219 (class 1259 OID 34236)
-- Name: opcxperfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.opcxperfil (
    id integer NOT NULL,
    codperfil character varying(4) NOT NULL,
    id_opcion integer
);


ALTER TABLE public.opcxperfil OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 34234)
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
-- TOC entry 2423 (class 0 OID 0)
-- Dependencies: 218
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
    estatus character(1) DEFAULT 'A'::bpchar,
    id_opcion integer
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
-- TOC entry 2424 (class 0 OID 0)
-- Dependencies: 212
-- Name: perfil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.perfil_id_seq OWNED BY public.perfil.id_perfil;


--
-- TOC entry 223 (class 1259 OID 58947)
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
    tipoper integer
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 67139)
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
-- TOC entry 2425 (class 0 OID 0)
-- Dependencies: 224
-- Name: persona_id_persona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persona_id_persona_seq OWNED BY public.persona.id_persona;


--
-- TOC entry 187 (class 1259 OID 16422)
-- Name: profesor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profesor (
    id integer NOT NULL,
    nombre character varying(20),
    apellido character varying(20),
    cedula character varying(10),
    telefono character varying(12),
    direccion character varying(100),
    sexo character varying(10),
    catedra character varying(20),
    fnacimiento date,
    id_catedra integer
);


ALTER TABLE public.profesor OWNER TO postgres;

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
-- TOC entry 2426 (class 0 OID 0)
-- Dependencies: 188
-- Name: profesor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profesor_id_seq OWNED BY public.profesor.id;


--
-- TOC entry 209 (class 1259 OID 16927)
-- Name: programa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.programa (
    id integer NOT NULL,
    nombre character varying(30),
    descripcion character varying(30)
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
-- TOC entry 2427 (class 0 OID 0)
-- Dependencies: 208
-- Name: programa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.programa_id_seq OWNED BY public.programa.id;


--
-- TOC entry 195 (class 1259 OID 16859)
-- Name: representante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.representante (
    id_representante integer NOT NULL,
    id_personal integer,
    id_estudiante integer
);


ALTER TABLE public.representante OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 75331)
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
-- TOC entry 2428 (class 0 OID 0)
-- Dependencies: 225
-- Name: representante_id_representante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.representante_id_representante_seq OWNED BY public.representante.id_representante;


--
-- TOC entry 231 (class 1259 OID 83755)
-- Name: tipo_movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_movimiento (
    id_tipo_move integer NOT NULL,
    nombre_move character varying(30)
);


ALTER TABLE public.tipo_movimiento OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 83753)
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
-- TOC entry 2429 (class 0 OID 0)
-- Dependencies: 230
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_movimiento_id_tipo_move_seq OWNED BY public.tipo_movimiento.id_tipo_move;


--
-- TOC entry 233 (class 1259 OID 83761)
-- Name: tipo_siniestro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_siniestro (
    id_tipos integer NOT NULL,
    nombrets character varying(30)
);


ALTER TABLE public.tipo_siniestro OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 83759)
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
-- TOC entry 2430 (class 0 OID 0)
-- Dependencies: 232
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
-- TOC entry 2431 (class 0 OID 0)
-- Dependencies: 197
-- Name: tipobien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipobien_id_seq OWNED BY public.tipobien.id_tipob;


--
-- TOC entry 198 (class 1259 OID 16888)
-- Name: tipoinstrumento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipoinstrumento (
    id integer NOT NULL,
    nombre character varying(20),
    descripcion character varying(20)
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
-- TOC entry 2432 (class 0 OID 0)
-- Dependencies: 199
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipoinstrumento_id_seq OWNED BY public.tipoinstrumento.id;


--
-- TOC entry 227 (class 1259 OID 75339)
-- Name: tipopersona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipopersona (
    id_tipopersona integer NOT NULL,
    nombretipoper character varying(20)
);


ALTER TABLE public.tipopersona OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 75337)
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
-- TOC entry 2433 (class 0 OID 0)
-- Dependencies: 226
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
    status character varying(5)
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
-- TOC entry 2434 (class 0 OID 0)
-- Dependencies: 214
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 222 (class 1259 OID 58939)
-- Name: vbien; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vbien AS
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
    tb.nombretb
   FROM (((public.bien bn
     JOIN public.marca mr ON (((bn.id_marca)::text = (mr.id_marca)::text)))
     JOIN public.tipobien tb ON (((bn.id_tipob)::text = (tb.id_tipob)::text)))
     JOIN public.modelo md ON (((bn.id_modelo)::text = (md.id_modelo)::text)));


ALTER TABLE public.vbien OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 91944)
-- Name: vmovientos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vmovientos AS
 SELECT tmv.nombre_move,
    mv.fecha_move,
    mv.justificacion,
    rs.nombre,
    mv.tipo_siniestro,
    bn.nombreb,
    bn.id
   FROM (((public.movimiento_bien mv
     JOIN public.tipo_movimiento tmv ON (((mv.tipo_mov)::text = (tmv.id_tipo_move)::text)))
     JOIN public.empleado rs ON (((mv.reponsable)::text = (rs.id)::text)))
     JOIN public.bien bn ON (((mv.id_bien)::text = (bn.id)::text)));


ALTER TABLE public.vmovientos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 34383)
-- Name: vrusuporperfil; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vrusuporperfil AS
 SELECT us.id,
    em.cedula,
    em.nombre,
    em.apellido,
    em.telefono,
    em.direccion,
    em.sexo,
    us.login,
    us.clave,
    em.cargo,
    em.fechanac,
    us.id_perfil,
    em.nacion
   FROM (public.usuario us
     JOIN public.empleado em ON (((us.id)::text = (em.id_usuario)::text)));


ALTER TABLE public.vrusuporperfil OWNER TO postgres;

--
-- TOC entry 2153 (class 2604 OID 16841)
-- Name: bien id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien ALTER COLUMN id SET DEFAULT nextval('public.bien_id_seq'::regclass);


--
-- TOC entry 2164 (class 2604 OID 17773)
-- Name: bitacora id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bitacora ALTER COLUMN id SET DEFAULT nextval('public.bitacora_id_seq'::regclass);


--
-- TOC entry 2154 (class 2604 OID 16850)
-- Name: cargo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo ALTER COLUMN id SET DEFAULT nextval('public.cargo_id_seq'::regclass);


--
-- TOC entry 2152 (class 2604 OID 16853)
-- Name: catedra id_catedra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catedra ALTER COLUMN id_catedra SET DEFAULT nextval('public.catedra_id_seq'::regclass);


--
-- TOC entry 2158 (class 2604 OID 16902)
-- Name: empleado id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id SET DEFAULT nextval('public.empleado_id_seq'::regclass);


--
-- TOC entry 2159 (class 2604 OID 34369)
-- Name: empleado id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id_usuario SET DEFAULT nextval('public.empleado_id_usuario_seq'::regclass);


--
-- TOC entry 2150 (class 2604 OID 16418)
-- Name: estudiante id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante ALTER COLUMN id SET DEFAULT nextval('public.estudiante_id_seq'::regclass);


--
-- TOC entry 2162 (class 2604 OID 16924)
-- Name: marca id_marca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca ALTER COLUMN id_marca SET DEFAULT nextval('public.marca_id_seq'::regclass);


--
-- TOC entry 2160 (class 2604 OID 16911)
-- Name: modelo id_modelo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo ALTER COLUMN id_modelo SET DEFAULT nextval('public.modelo_id_seq'::regclass);


--
-- TOC entry 2161 (class 2604 OID 16917)
-- Name: modulo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo ALTER COLUMN id SET DEFAULT nextval('public.modulo_id_seq'::regclass);


--
-- TOC entry 2172 (class 2604 OID 83752)
-- Name: movimiento_bien id_mov_bien; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien ALTER COLUMN id_mov_bien SET DEFAULT nextval('public.movimiento_bien_id_mov_bien_seq'::regclass);


--
-- TOC entry 2168 (class 2604 OID 34191)
-- Name: opcion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion ALTER COLUMN id SET DEFAULT nextval('public.opcion_id_seq'::regclass);


--
-- TOC entry 2169 (class 2604 OID 34239)
-- Name: opcxperfil id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil ALTER COLUMN id SET DEFAULT nextval('public.opcxperfil_id_seq'::regclass);


--
-- TOC entry 2165 (class 2604 OID 34157)
-- Name: perfil id_perfil; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil ALTER COLUMN id_perfil SET DEFAULT nextval('public.perfil_id_seq'::regclass);


--
-- TOC entry 2170 (class 2604 OID 67141)
-- Name: persona id_persona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona ALTER COLUMN id_persona SET DEFAULT nextval('public.persona_id_persona_seq'::regclass);


--
-- TOC entry 2151 (class 2604 OID 16427)
-- Name: profesor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor ALTER COLUMN id SET DEFAULT nextval('public.profesor_id_seq'::regclass);


--
-- TOC entry 2163 (class 2604 OID 16930)
-- Name: programa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa ALTER COLUMN id SET DEFAULT nextval('public.programa_id_seq'::regclass);


--
-- TOC entry 2155 (class 2604 OID 75333)
-- Name: representante id_representante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.representante ALTER COLUMN id_representante SET DEFAULT nextval('public.representante_id_representante_seq'::regclass);


--
-- TOC entry 2173 (class 2604 OID 83758)
-- Name: tipo_movimiento id_tipo_move; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento ALTER COLUMN id_tipo_move SET DEFAULT nextval('public.tipo_movimiento_id_tipo_move_seq'::regclass);


--
-- TOC entry 2174 (class 2604 OID 83764)
-- Name: tipo_siniestro id_tipos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_siniestro ALTER COLUMN id_tipos SET DEFAULT nextval('public.tipo_siniestro_id_tipos_seq'::regclass);


--
-- TOC entry 2156 (class 2604 OID 16875)
-- Name: tipobien id_tipob; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipobien ALTER COLUMN id_tipob SET DEFAULT nextval('public.tipobien_id_seq'::regclass);


--
-- TOC entry 2157 (class 2604 OID 16893)
-- Name: tipoinstrumento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipoinstrumento ALTER COLUMN id SET DEFAULT nextval('public.tipoinstrumento_id_seq'::regclass);


--
-- TOC entry 2171 (class 2604 OID 75342)
-- Name: tipopersona id_tipopersona; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona ALTER COLUMN id_tipopersona SET DEFAULT nextval('public.tipopersona_id_tipopersona_seq'::regclass);


--
-- TOC entry 2167 (class 2604 OID 34166)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- TOC entry 2360 (class 0 OID 16836)
-- Dependencies: 190
-- Data for Name: bien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bien (modelo, serial, id, marca, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo) VALUES ('null', 'Mobiliario', 23, 'null', '', '', '', NULL, NULL, NULL, NULL);
INSERT INTO public.bien (modelo, serial, id, marca, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo) VALUES ('6', '1', 29, '1', '1', '2', '12', 3, 'cuatro', NULL, NULL);
INSERT INTO public.bien (modelo, serial, id, marca, medida, cantidad, ninventario, id_tipob, nombreb, id_marca, id_modelo) VALUES ('modelo', '123', 25, '3', '3132', '3', '23', 3, 'violin', 6, 1);


--
-- TOC entry 2435 (class 0 OID 0)
-- Dependencies: 191
-- Name: bien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bien_id_seq', 29, true);


--
-- TOC entry 2380 (class 0 OID 17768)
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


--
-- TOC entry 2436 (class 0 OID 0)
-- Dependencies: 211
-- Name: bitacora_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bitacora_id_seq', 181, true);


--
-- TOC entry 2363 (class 0 OID 16847)
-- Dependencies: 193
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (1, 'ADMINISTRADOR', 'REGISTRA ESTUDIANTES', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (23, 'CARGONOM', 'SEGIO ES DESCRIPCION', NULL);
INSERT INTO public.cargo (id, nombrecar, descripcion, codcar) VALUES (22, 'SECRETARIA', 'REGISTRAR ESTUDIANTE', NULL);


--
-- TOC entry 2437 (class 0 OID 0)
-- Dependencies: 192
-- Name: cargo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cargo_id_seq', 25, true);


--
-- TOC entry 2359 (class 0 OID 16827)
-- Dependencies: 189
-- Data for Name: catedra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.catedra (id_catedra, nombrec, descripcion) VALUES (4, 'CATEDRA', 'DESCRICION');


--
-- TOC entry 2438 (class 0 OID 0)
-- Dependencies: 194
-- Name: catedra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.catedra_id_seq', 5, true);


--
-- TOC entry 2370 (class 0 OID 16897)
-- Dependencies: 200
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.empleado (id, nombre, apellido, cedula, direccion, telefono, sexo, cargo, fechanac, usuario, contrasena, nacion, id_usuario) VALUES (6, 'CESAR', 'MOLINA', '11279233', 'YARITAGUA', '04145083262', 'M', 'ADMINISTRADOR', '1996-05-08', NULL, NULL, 'V         ', 4);


--
-- TOC entry 2439 (class 0 OID 0)
-- Dependencies: 201
-- Name: empleado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_seq', 6, true);


--
-- TOC entry 2440 (class 0 OID 0)
-- Dependencies: 220
-- Name: empleado_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_usuario_seq', 2, true);


--
-- TOC entry 2355 (class 0 OID 16413)
-- Dependencies: 185
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estudiante (id, cedula, nombre, direccion, telefono, catedra, sexo, apellido, fnacimiento, nacion) VALUES (23, '25646854', 'AUGUSTO', 'YARITAGUA LA PASTORA', '04125083262', 'CATEDRA', 'M', 'MOLINA', '1996-08-05', 'V');


--
-- TOC entry 2441 (class 0 OID 0)
-- Dependencies: 186
-- Name: estudiante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estudiante_id_seq', 23, true);


--
-- TOC entry 2377 (class 0 OID 16921)
-- Dependencies: 207
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (6, 'MARCAE', 'DESCRIPCION');
INSERT INTO public.marca (id_marca, nombrema, descripcion) VALUES (2, 'NIKE', 'ZAPATOS NIKE');


--
-- TOC entry 2442 (class 0 OID 0)
-- Dependencies: 206
-- Name: marca_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.marca_id_seq', 6, true);


--
-- TOC entry 2373 (class 0 OID 16908)
-- Dependencies: 203
-- Data for Name: modelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (1, 'modelo', NULL);
INSERT INTO public.modelo (id_modelo, nombremo, id_marca) VALUES (2, 'nombreM', 2);


--
-- TOC entry 2443 (class 0 OID 0)
-- Dependencies: 202
-- Name: modelo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modelo_id_seq', 2, true);


--
-- TOC entry 2375 (class 0 OID 16914)
-- Dependencies: 205
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modulo (id, nombre, descripcion) VALUES (1, 'DESC', 'MODULO');


--
-- TOC entry 2444 (class 0 OID 0)
-- Dependencies: 204
-- Name: modulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modulo_id_seq', 1, true);


--
-- TOC entry 2397 (class 0 OID 83749)
-- Dependencies: 229
-- Data for Name: movimiento_bien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento_bien (id_mov_bien, tipo_mov, fecha_move, justificacion, reponsable, tipo_siniestro, id_bien) VALUES (2, 1, '2018-08-10', 'JUSTIFICADO', 6, 'EXTRAVIADO', NULL);
INSERT INTO public.movimiento_bien (id_mov_bien, tipo_mov, fecha_move, justificacion, reponsable, tipo_siniestro, id_bien) VALUES (3, 1, '2018-08-29', 'SE ME OLVIDO EN EL YOUTONG', 6, 'EXTRAVIADO', 25);
INSERT INTO public.movimiento_bien (id_mov_bien, tipo_mov, fecha_move, justificacion, reponsable, tipo_siniestro, id_bien) VALUES (4, 1, '2018-08-30', 'desincorporado por que le faltan piesas', 6, NULL, 25);


--
-- TOC entry 2445 (class 0 OID 0)
-- Dependencies: 228
-- Name: movimiento_bien_id_mov_bien_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_bien_id_mov_bien_seq', 4, true);


--
-- TOC entry 2387 (class 0 OID 34188)
-- Dependencies: 217
-- Data for Name: opcion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.opcion (id, nombre) VALUES (1, 'ADMINISTRADOR');
INSERT INTO public.opcion (id, nombre) VALUES (2, 'USUARIO');


--
-- TOC entry 2446 (class 0 OID 0)
-- Dependencies: 216
-- Name: opcion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcion_id_seq', 1, false);


--
-- TOC entry 2389 (class 0 OID 34236)
-- Dependencies: 219
-- Data for Name: opcxperfil; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2447 (class 0 OID 0)
-- Dependencies: 218
-- Name: opcxperfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.opcxperfil_id_seq', 1, false);


--
-- TOC entry 2383 (class 0 OID 34154)
-- Dependencies: 213
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.perfil (id_perfil, codperfil, descrip, estatus, id_opcion) VALUES (6, '1', 'ADMINISTRADOR', 's', 1);


--
-- TOC entry 2448 (class 0 OID 0)
-- Dependencies: 212
-- Name: perfil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.perfil_id_seq', 2, true);


--
-- TOC entry 2391 (class 0 OID 58947)
-- Dependencies: 223
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.persona (id_persona, nombreper, apellidoper, cedulaper, telefonoper, generoper, direccionper, fnacimientoper, tipoper) VALUES (1, 'CESAR', 'MOLINA', '25646854', '04125083262', 'M', 'YARITAGUA', '1996-08-05', 5);


--
-- TOC entry 2449 (class 0 OID 0)
-- Dependencies: 224
-- Name: persona_id_persona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persona_id_persona_seq', 1, true);


--
-- TOC entry 2357 (class 0 OID 16422)
-- Dependencies: 187
-- Data for Name: profesor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.profesor (id, nombre, apellido, cedula, telefono, direccion, sexo, catedra, fnacimiento, id_catedra) VALUES (3, 'josefa', 'gomez', '25646854', '04125083262', 'adasd', 'F', 'CATEDRA', '2018-07-13', 4);


--
-- TOC entry 2450 (class 0 OID 0)
-- Dependencies: 188
-- Name: profesor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profesor_id_seq', 3, true);


--
-- TOC entry 2379 (class 0 OID 16927)
-- Dependencies: 209
-- Data for Name: programa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2451 (class 0 OID 0)
-- Dependencies: 208
-- Name: programa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.programa_id_seq', 2, true);


--
-- TOC entry 2365 (class 0 OID 16859)
-- Dependencies: 195
-- Data for Name: representante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.representante (id_representante, id_personal, id_estudiante) VALUES (1, NULL, NULL);


--
-- TOC entry 2452 (class 0 OID 0)
-- Dependencies: 225
-- Name: representante_id_representante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.representante_id_representante_seq', 1, true);


--
-- TOC entry 2399 (class 0 OID 83755)
-- Dependencies: 231
-- Data for Name: tipo_movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (1, 'REPARAR');
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (2, 'ASIGNAR');
INSERT INTO public.tipo_movimiento (id_tipo_move, nombre_move) VALUES (3, 'DESINCORPORAR');


--
-- TOC entry 2453 (class 0 OID 0)
-- Dependencies: 230
-- Name: tipo_movimiento_id_tipo_move_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_movimiento_id_tipo_move_seq', 3, true);


--
-- TOC entry 2401 (class 0 OID 83761)
-- Dependencies: 233
-- Data for Name: tipo_siniestro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_siniestro (id_tipos, nombrets) VALUES (1, 'ROBADO');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets) VALUES (2, 'EXTRAVIADO');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets) VALUES (3, 'QUEMADO');
INSERT INTO public.tipo_siniestro (id_tipos, nombrets) VALUES (4, 'DAÑADO');


--
-- TOC entry 2454 (class 0 OID 0)
-- Dependencies: 232
-- Name: tipo_siniestro_id_tipos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_siniestro_id_tipos_seq', 4, true);


--
-- TOC entry 2366 (class 0 OID 16870)
-- Dependencies: 196
-- Data for Name: tipobien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipobien (id_tipob, nombretb, descripcion) VALUES (3, 'TIPOB', 'DESC');


--
-- TOC entry 2455 (class 0 OID 0)
-- Dependencies: 197
-- Name: tipobien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipobien_id_seq', 3, true);


--
-- TOC entry 2368 (class 0 OID 16888)
-- Dependencies: 198
-- Data for Name: tipoinstrumento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipoinstrumento (id, nombre, descripcion) VALUES (1, 'nom', 'descripcion');


--
-- TOC entry 2456 (class 0 OID 0)
-- Dependencies: 199
-- Name: tipoinstrumento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipoinstrumento_id_seq', 2, true);


--
-- TOC entry 2395 (class 0 OID 75339)
-- Dependencies: 227
-- Data for Name: tipopersona; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (1, 'EMPLEADO');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (2, 'ESTUDIANTE');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (3, 'PROFESOR');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (4, 'USUARIO');
INSERT INTO public.tipopersona (id_tipopersona, nombretipoper) VALUES (5, 'REPRESENTANTE');


--
-- TOC entry 2457 (class 0 OID 0)
-- Dependencies: 226
-- Name: tipopersona_id_tipopersona_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipopersona_id_tipopersona_seq', 5, true);


--
-- TOC entry 2385 (class 0 OID 34163)
-- Dependencies: 215
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id, clave, id_perfil, login, status) VALUES (4, '123', 6, 'ADMIN', 'TRUE');


--
-- TOC entry 2458 (class 0 OID 0)
-- Dependencies: 214
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 4, true);


--
-- TOC entry 2180 (class 2606 OID 91951)
-- Name: bien bien_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_id_key UNIQUE (id);


--
-- TOC entry 2182 (class 2606 OID 34219)
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);


--
-- TOC entry 2178 (class 2606 OID 50720)
-- Name: catedra catedra_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catedra
    ADD CONSTRAINT catedra_id_key UNIQUE (id_catedra);


--
-- TOC entry 2188 (class 2606 OID 34160)
-- Name: perfil codperfil; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT codperfil PRIMARY KEY (codperfil);


--
-- TOC entry 2198 (class 2606 OID 34241)
-- Name: opcxperfil idopcper; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT idopcper PRIMARY KEY (id);


--
-- TOC entry 2186 (class 2606 OID 17781)
-- Name: modelo modelo_id_marca_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_id_marca_key UNIQUE (id_marca);


--
-- TOC entry 2206 (class 2606 OID 83770)
-- Name: movimiento_bien movimiento_bien_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_pkey PRIMARY KEY (id_mov_bien);


--
-- TOC entry 2196 (class 2606 OID 34305)
-- Name: opcion opcion_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcion
    ADD CONSTRAINT opcion_id_key UNIQUE (id);


--
-- TOC entry 2190 (class 2606 OID 34290)
-- Name: perfil perfil_codperfil_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_codperfil_key UNIQUE (codperfil);


--
-- TOC entry 2192 (class 2606 OID 34312)
-- Name: perfil perfil_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_id_key UNIQUE (id_perfil);


--
-- TOC entry 2200 (class 2606 OID 75344)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2176 (class 2606 OID 50713)
-- Name: profesor profesor_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_key UNIQUE (id);


--
-- TOC entry 2208 (class 2606 OID 83768)
-- Name: tipo_movimiento tipo_movimiento_id_tipo_move_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_id_tipo_move_key UNIQUE (id_tipo_move);


--
-- TOC entry 2210 (class 2606 OID 83766)
-- Name: tipo_movimiento tipo_movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_pkey PRIMARY KEY (id_tipo_move);


--
-- TOC entry 2184 (class 2606 OID 58933)
-- Name: tipobien tipobien_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipobien
    ADD CONSTRAINT tipobien_id_key UNIQUE (id_tipob);


--
-- TOC entry 2202 (class 2606 OID 75348)
-- Name: tipopersona tipopersona_id_tipopersona_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona
    ADD CONSTRAINT tipopersona_id_tipopersona_key UNIQUE (id_tipopersona);


--
-- TOC entry 2204 (class 2606 OID 75346)
-- Name: tipopersona tipopersona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipopersona
    ADD CONSTRAINT tipopersona_pkey PRIMARY KEY (id_tipopersona);


--
-- TOC entry 2194 (class 2606 OID 34360)
-- Name: usuario usuario_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_key UNIQUE (id);


--
-- TOC entry 2231 (class 2620 OID 42479)
-- Name: marca tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.marca FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2222 (class 2620 OID 42480)
-- Name: catedra tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.catedra FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2228 (class 2620 OID 42481)
-- Name: empleado tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.empleado FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2220 (class 2620 OID 42482)
-- Name: estudiante tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.estudiante FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2229 (class 2620 OID 42484)
-- Name: modelo tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.modelo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2230 (class 2620 OID 42485)
-- Name: modulo tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.modulo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2233 (class 2620 OID 42486)
-- Name: perfil tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.perfil FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2232 (class 2620 OID 42488)
-- Name: programa tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.programa FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2225 (class 2620 OID 42489)
-- Name: representante tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.representante FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2226 (class 2620 OID 42490)
-- Name: tipobien tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.tipobien FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2227 (class 2620 OID 42491)
-- Name: tipoinstrumento tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.tipoinstrumento FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2224 (class 2620 OID 42493)
-- Name: cargo tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.cargo FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2223 (class 2620 OID 42494)
-- Name: bien tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.bien FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2234 (class 2620 OID 42520)
-- Name: usuario tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE ON public.usuario FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2221 (class 2620 OID 50746)
-- Name: profesor tbl_atributos_tg_audit; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR DELETE OR UPDATE ON public.profesor FOR EACH ROW EXECUTE PROCEDURE public.fn_log_audit();


--
-- TOC entry 2212 (class 2606 OID 58934)
-- Name: bien bien_id_tipob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bien
    ADD CONSTRAINT bien_id_tipob_fkey FOREIGN KEY (id_tipob) REFERENCES public.tipobien(id_tipob);


--
-- TOC entry 2215 (class 2606 OID 34247)
-- Name: opcxperfil codperfil; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT codperfil FOREIGN KEY (codperfil) REFERENCES public.perfil(codperfil);


--
-- TOC entry 2213 (class 2606 OID 34407)
-- Name: empleado empleado_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id);


--
-- TOC entry 2219 (class 2606 OID 91952)
-- Name: movimiento_bien movimiento_bien_id_bien_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_id_bien_fkey FOREIGN KEY (id_bien) REFERENCES public.bien(id);


--
-- TOC entry 2218 (class 2606 OID 83771)
-- Name: movimiento_bien movimiento_bien_tipo_mov_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento_bien
    ADD CONSTRAINT movimiento_bien_tipo_mov_fkey FOREIGN KEY (tipo_mov) REFERENCES public.tipo_movimiento(id_tipo_move);


--
-- TOC entry 2216 (class 2606 OID 34306)
-- Name: opcxperfil opcxperfil_id_opcion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.opcxperfil
    ADD CONSTRAINT opcxperfil_id_opcion_fkey FOREIGN KEY (id_opcion) REFERENCES public.opcion(id);


--
-- TOC entry 2217 (class 2606 OID 75349)
-- Name: persona persona_tipoper_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_tipoper_fkey FOREIGN KEY (tipoper) REFERENCES public.tipopersona(id_tipopersona);


--
-- TOC entry 2211 (class 2606 OID 50726)
-- Name: profesor profesor_id_catedra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profesor
    ADD CONSTRAINT profesor_id_catedra_fkey FOREIGN KEY (id_catedra) REFERENCES public.catedra(id_catedra);


--
-- TOC entry 2214 (class 2606 OID 34330)
-- Name: usuario usuario_id_perfil_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES public.perfil(id_perfil);


-- Completed on 2018-08-30 21:08:20

--
-- PostgreSQL database dump complete
--

