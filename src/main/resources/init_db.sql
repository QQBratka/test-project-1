CREATE TABLE IF NOT EXISTS public.clients
(
    id bigint NOT NULL,
    created_time text COLLATE pg_catalog."default" NOT NULL,
    deleted_time text COLLATE pg_catalog."default",
    CONSTRAINT clients_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.clients
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.personal_informations
(
    id bigint NOT NULL,
    client_id bigint NOT NULL,
    fio text COLLATE pg_catalog."default",
    passport_number text COLLATE pg_catalog."default",
    birth_date text COLLATE pg_catalog."default",
    created_time text COLLATE pg_catalog."default" NOT NULL,
    deleted_time text COLLATE pg_catalog."default",
    CONSTRAINT birth_date_u UNIQUE (birth_date),
    CONSTRAINT personal_informations_fk FOREIGN KEY (client_id)
        REFERENCES client_info.clients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.personal_informations
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.phone_numbers
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    first_number text COLLATE pg_catalog."default",
    second_number text COLLATE pg_catalog."default",
    created_time text COLLATE pg_catalog."default" NOT NULL,
    deleted_time text COLLATE pg_catalog."default",
    CONSTRAINT phone_numbers_fk FOREIGN KEY (user_id)
        REFERENCES client_info.clients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.phone_numbers
    OWNER to postgres;