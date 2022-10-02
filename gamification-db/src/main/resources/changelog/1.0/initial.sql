--prerequisites: создана бд gamifiication

-- liquibase formatted sql
CREATE EXTENSION "uuid-ossp";

--changeset nkochnev:create-user-info
CREATE TABLE user_info
(
    id      UUID        DEFAULT uuid_generate_v1()   PRIMARY KEY,
    money   INT         DEFAULT 0                    NOT NULL,
    role    VARCHAR(50)                              NOT NULL
);
COMMENT ON TABLE  user_info         IS 'Инфо о пользователе';
COMMENT ON COLUMN user_info.id      IS 'Идентификатор пользователя';
COMMENT ON COLUMN user_info.role    IS 'Роль пользователя';
--rollback DROP TABLE user_info

--changeset nkochnev:create-teams
CREATE TABLE teams
(
    id         UUID         DEFAULT uuid_generate_v1()        PRIMARY KEY,
    name       VARCHAR(50)                                    NOT NULL,
    team_type  VARCHAR(50)                                    NOT NULL
);
COMMENT ON TABLE  user_info         IS 'Инфо о пользователе';
COMMENT ON COLUMN user_info.id      IS 'Идентификатор пользователя';
COMMENT ON COLUMN user_info.role    IS 'Роль пользователя';
--rollback DROP TABLE teams

--changeset nkochnev:create-user-teams
CREATE TABLE user_teams
(
    user_id    UUID    REFERENCES user_info (id) ON DELETE CASCADE,
    team_id    UUID    REFERENCES teams (id)     ON DELETE CASCADE,
    PRIMARY KEY (user_id, team_id)
);
--rollback DROP TABLE user_teams

--changeset nkochnev:create-items
CREATE TABLE items
(
    id           UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    name         VARCHAR(100)                                        NOT NULL,
    description  VARCHAR(255)                                        NULL,
    money_price  INT            DEFAULT 0                            NOT NULL,
    nft_price    INT            DEFAULT 0                            NOT NULL,
    created_date TIMESTAMP      DEFAULT now()                        NOT NULL,
    created_by   UUID                                                NOT NULL,
    updated_date TIMESTAMP                                           NOT NULL,
    updated_by   UUID                                                NOT NULL
);
COMMENT ON TABLE  items              IS 'Предмет в маркетплейс';
COMMENT ON COLUMN items.id           IS 'Идентификатор предмета';
COMMENT ON COLUMN items.name         IS 'Название предмета';
COMMENT ON COLUMN items.description  IS 'Описание предмета';
COMMENT ON COLUMN items.money_price  IS 'Цена предмета в монетах';
COMMENT ON COLUMN items.nft_price    IS 'Цена предмета в сертификатах';
COMMENT ON COLUMN items.created_date IS 'Дата создания';
COMMENT ON COLUMN items.created_by   IS 'Кем создано';
COMMENT ON COLUMN items.updated_date IS 'Дата обновления';
COMMENT ON COLUMN items.updated_by   IS 'Кем обновлено';
--rollback DROP TABLE items

--changeset nkochnev:create-operations
CREATE TABLE operations
(
    id                    UUID          DEFAULT uuid_generate_v1()   PRIMARY KEY,
    name                  VARCHAR(100)                               NOT NULL,
    description           VARCHAR(255)                               NULL,
    money_reward          INT           DEFAULT 0                    NOT NULL,
    nft_reward            INT           DEFAULT 0                    NOT NULL,
    role                  VARCHAR(50)                                NULL,
    can_be_changed_reward BOOLEAN,
    operation_type        VARCHAR(50)                                NOT NULL,
    created_date          TIMESTAMP     DEFAULT now()                NOT NULL,
    created_by            UUID                                       NOT NULL,
    updated_date          TIMESTAMP                                  NOT NULL,
    updated_by            UUID                                       NOT NULL
);
COMMENT ON TABLE  operations                         IS 'Операция в маркетплейс';
COMMENT ON COLUMN operations.id                      IS 'Идентификатор операции';
COMMENT ON COLUMN operations.name                    IS 'Название операции';
COMMENT ON COLUMN operations.description             IS 'Описание операции';
COMMENT ON COLUMN operations.money_reward            IS 'Сумма операции в монетах';
COMMENT ON COLUMN operations.nft_reward              IS 'Сумма операции в сертификатах';
COMMENT ON COLUMN operations.role                    IS 'Необходимая роль для операции';
COMMENT ON COLUMN operations.can_be_changed_reward   IS 'Возможность менять награду при награждении';
COMMENT ON COLUMN operations.created_date            IS 'Дата создания';
COMMENT ON COLUMN operations.created_by              IS 'Кем создано';
COMMENT ON COLUMN operations.updated_date            IS 'Дата обновления';
COMMENT ON COLUMN operations.updated_by              IS 'Кем обновлено';
--rollback DROP TABLE operations

--changeset nkochnev:create-transaction_history
CREATE TABLE transaction_history
(
    id                     UUID           DEFAULT uuid_generate_v1()    PRIMARY KEY,
    item_id                UUID                                         NULL,
    money                  INT                                          NULL,
    nft                    INT            DEFAULT 0                     NULL,
    user_id                INT            DEFAULT 0                     NOT NULL,
    created_date           TIMESTAMP      DEFAULT now()                 NOT NULL,
    transaction_initiator  UUID                                         NOT NULL
);
COMMENT ON TABLE  transaction_history                       IS 'Транзакции';
COMMENT ON COLUMN transaction_history.id                    IS 'Идентификатор транзакции';
COMMENT ON COLUMN transaction_history.item_id               IS 'В случае обмена id предмета';
COMMENT ON COLUMN transaction_history.money                 IS 'Сумма в монетах';
COMMENT ON COLUMN transaction_history.nft                   IS 'Сумма в nft';
COMMENT ON COLUMN transaction_history.user_id               IS 'На кого проведена транзакция';
COMMENT ON COLUMN transaction_history.created_date          IS 'Дата создания';
COMMENT ON COLUMN transaction_history.transaction_initiator IS 'Инициатор транзакции';
--rollback DROP TABLE transaction_history