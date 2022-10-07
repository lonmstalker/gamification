-- prerequisites: создана бд game
-- liquibase formatted sql

--changeset nkochnev:create-uuid
CREATE EXTENSION "uuid-ossp";
--rollbback DROP EXTENSION "uuid-ossp";

--changeset nkochnev:create-wallet
CREATE TABLE wallet
(
    wallet_id UUID        DEFAULT uuid_generate_v1()   PRIMARY KEY,
    money     INT         DEFAULT 0                    NOT NULL,
    nft       INT         DEFAULT 0                    NOT NULL,
    role      VARCHAR(50)                              NOT NULL
);
COMMENT ON TABLE  wallet           IS 'Кошелек пользователя';
COMMENT ON COLUMN wallet.wallet_id IS 'Идентификатор кошелька';
COMMENT ON COLUMN wallet.money     IS 'Денег в монетах';
COMMENT ON COLUMN wallet.nft       IS 'Денег в сертификатах';
COMMENT ON COLUMN wallet.role      IS 'Роль пользователя';
--rollback DROP TABLE wallet;

--changeset nkochnev:create-teams
CREATE TABLE teams
(
    team_id    UUID         DEFAULT uuid_generate_v1()        PRIMARY KEY,
    name       VARCHAR(50)                                    NOT NULL,
    team_type  VARCHAR(50)                                    NOT NULL
);
COMMENT ON TABLE  teams              IS 'Инфо о пользователе';
COMMENT ON COLUMN teams.team_id      IS 'Идентификатор команды';
COMMENT ON COLUMN teams.team_type    IS 'Роль пользователя';
--rollback DROP TABLE teams;

--changeset nkochnev:create-wallet_teams
CREATE TABLE wallet_teams
(
    wallet_id  UUID    REFERENCES wallet (wallet_id)    ON DELETE CASCADE,
    team_id    UUID    REFERENCES teams (team_id)       ON DELETE CASCADE,
    PRIMARY KEY (wallet_id, team_id)
);
--rollback DROP TABLE wallet_teams;

--changeset nkochnev:create-items
CREATE TABLE items
(
    item_id      UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    name         VARCHAR(100)                                        NOT NULL,
    image_uri    VARCHAR(255)                                        NOT NULL,
    description  VARCHAR(255)                                        NULL,
    money_price  INT            DEFAULT 0                            NOT NULL,
    nft_price    INT            DEFAULT 0                            NOT NULL,
    created_date TIMESTAMP      DEFAULT now()                        NOT NULL,
    created_by   UUID                                                NOT NULL,
    updated_date TIMESTAMP                                           NOT NULL,
    updated_by   UUID                                                NOT NULL
);
COMMENT ON TABLE  items              IS 'Предмет в маркетплейс';
COMMENT ON COLUMN items.item_id      IS 'Идентификатор предмета';
COMMENT ON COLUMN items.name         IS 'Название предмета';
COMMENT ON COLUMN items.description  IS 'Описание предмета';
COMMENT ON COLUMN items.image_uri    IS 'Ссылка на картинку';
COMMENT ON COLUMN items.money_price  IS 'Цена предмета в монетах';
COMMENT ON COLUMN items.nft_price    IS 'Цена предмета в сертификатах';
COMMENT ON COLUMN items.created_date IS 'Дата создания';
COMMENT ON COLUMN items.created_by   IS 'Кем создано';
COMMENT ON COLUMN items.updated_date IS 'Дата обновления';
COMMENT ON COLUMN items.updated_by   IS 'Кем обновлено';
--rollback DROP TABLE items;

--changeset nkochnev:create-actions
CREATE TABLE actions
(
    action_id             UUID          DEFAULT uuid_generate_v1()   PRIMARY KEY,
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
COMMENT ON TABLE  actions                         IS 'Действие, за которое награждают';
COMMENT ON COLUMN actions.action_id               IS 'Идентификатор действия';
COMMENT ON COLUMN actions.name                    IS 'Название операции';
COMMENT ON COLUMN actions.description             IS 'Описание операции';
COMMENT ON COLUMN actions.money_reward            IS 'Сумма операции в монетах';
COMMENT ON COLUMN actions.nft_reward              IS 'Сумма операции в сертификатах';
COMMENT ON COLUMN actions.role                    IS 'Необходимая роль для операции';
COMMENT ON COLUMN actions.can_be_changed_reward   IS 'Возможность менять награду при награждении';
COMMENT ON COLUMN actions.created_date            IS 'Дата создания';
COMMENT ON COLUMN actions.created_by              IS 'Кем создано';
COMMENT ON COLUMN actions.updated_date            IS 'Дата обновления';
COMMENT ON COLUMN actions.updated_by              IS 'Кем обновлено';
--rollback DROP TABLE operations;

--changeset nkochnev:create-transaction_history
CREATE TABLE transaction_history
(
    transaction_id         UUID           DEFAULT uuid_generate_v1()    PRIMARY KEY,
    item_id                UUID                                         NULL,
    money                  INT                                          NULL,
    nft                    INT            DEFAULT 0                     NULL,
    user_id                INT            DEFAULT 0                     NOT NULL,
    created_date           TIMESTAMP      DEFAULT now()                 NOT NULL,
    transaction_initiator  UUID                                         NOT NULL
);
COMMENT ON TABLE  transaction_history                       IS 'Транзакции';
COMMENT ON COLUMN transaction_history.transaction_id        IS 'Идентификатор транзакции';
COMMENT ON COLUMN transaction_history.item_id               IS 'В случае обмена id предмета';
COMMENT ON COLUMN transaction_history.money                 IS 'Сумма в монетах';
COMMENT ON COLUMN transaction_history.nft                   IS 'Сумма в nft';
COMMENT ON COLUMN transaction_history.user_id               IS 'На кого проведена транзакция';
COMMENT ON COLUMN transaction_history.created_date          IS 'Дата создания';
COMMENT ON COLUMN transaction_history.transaction_initiator IS 'Инициатор транзакции';
--rollback DROP TABLE transaction_history;

--changeset nkochnev:create-triggers
CREATE TABLE triggers
(
    trigger_id   UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    name         VARCHAR(100)                                        NOT NULL,
    user_id      UUID                                                NULL,
    group_id     UUID                                                NULL,
    trigger_type VARCHAR(20)                                         NOT NULL,
    reward_time  VARCHAR(20)                                         NOT NULL,
    action_id    UUID                                                NOT NULL,
    created_date TIMESTAMP      DEFAULT now()                        NOT NULL,
    created_by   UUID                                                NOT NULL,
    updated_date TIMESTAMP                                           NOT NULL,
    updated_by   UUID                                                NOT NULL
);
COMMENT ON TABLE  triggers              IS 'Триггер';
COMMENT ON COLUMN triggers.trigger_id   IS 'Идентификатор триггера';
COMMENT ON COLUMN triggers.name         IS 'Название триггера';
COMMENT ON COLUMN triggers.user_id      IS 'id пользователя';
COMMENT ON COLUMN triggers.group_id     IS 'id команды';
COMMENT ON COLUMN triggers.trigger_type IS 'Тип триггера';
COMMENT ON COLUMN triggers.reward_time  IS 'Время награждения';
COMMENT ON COLUMN triggers.action_id    IS 'Id действия';
COMMENT ON COLUMN triggers.created_date IS 'Дата создания';
COMMENT ON COLUMN triggers.created_by   IS 'Кем создано';
COMMENT ON COLUMN triggers.updated_date IS 'Дата обновления';
COMMENT ON COLUMN triggers.updated_by   IS 'Кем обновлено';
--rollback DROP TABLE triggers;