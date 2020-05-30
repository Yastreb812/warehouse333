DROP TABLE IF EXISTS items;
CREATE TABLE items
(
    id    SERIAL PRIMARY KEY ,
    volume  INTEGER NOT NULL ,
    value INTEGER NOT NULL ,
    art BOOLEAN NOT NULL
);
DROP TABLE IF EXISTS locations;
CREATE TABLE locations
(
    id    SERIAL PRIMARY KEY ,
    ratemin  INTEGER NOT NULL ,
    ratemax  INTEGER NOT NULL
);

INSERT INTO items (id, volume, value, art) VALUES
(1, '11', '111', true),
(2, '1500', '111', true),
(3, '1001', '111', false),
(4, '11', '111', true),
(5, '22', '222', false);



INSERT INTO locations (id, ratemin, ratemax) VALUES
(1, '100', '2000'),
(2, '20', '1230');