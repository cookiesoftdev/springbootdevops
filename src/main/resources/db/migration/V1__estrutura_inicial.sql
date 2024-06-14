CREATE SEQUENCE IF NOT EXISTS public.category_id_seq;

CREATE TABLE IF NOT EXISTS public.category (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('public.category_id_seq'),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status BOOLEAN DEFAULT TRUE
);