Create Database Vistaverde;
Use Vistaverde;

-- =========================
-- TABLA PROPIETARIO
-- =========================
CREATE TABLE propietario (
    Dpi INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

drop Table propietario;

-- =========================
-- TABLA CONDOMINIO
-- =========================
CREATE TABLE condominio (
    id_condominio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- =========================
-- TABLA CASA
-- =========================
CREATE TABLE casa (
    id_casa INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    direccion VARCHAR(200),

    Dpi INT,
    id_condominio INT,

    CONSTRAINT fk_casa_propietario
        FOREIGN KEY (Dpi)
        REFERENCES propietario(Dpi),

    CONSTRAINT fk_casa_condominio
        FOREIGN KEY (id_condominio)
        REFERENCES condominio(id_condominio)
);

-- =========================
-- TABLA PAGO
-- =========================
CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,

    mes VARCHAR(20) NOT NULL,
    anio INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20),

    id_casa INT,

    CONSTRAINT fk_pago_casa
        FOREIGN KEY (id_casa)
        REFERENCES casa(id_casa)
);

-- =========================
-- TABLA LOGIN
-- =========================
CREATE TABLE login (
    id_login INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL
);

Insert into login values (1,"iusr_vistaverde","R3sidencial2026%");

select * from login;