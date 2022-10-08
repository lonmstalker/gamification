-- prerequisites: создана бд game
-- liquibase formatted sql

--changeset nkochnev:create-uuid
CREATE EXTENSION "uuid-ossp";
--rollbback DROP EXTENSION "uuid-ossp";

--changeset nkochnev:create-wallet
CREATE TABLE wallet
(
    wallet_id    UUID       DEFAULT uuid_generate_v1()   PRIMARY KEY,
    role         VARCHAR(50)                              NOT NULL,
    first_name   VARCHAR(100)                             NOT NULL,
    last_name    VARCHAR(100)                             NULL,
    middle_name  VARCHAR(100)                             NULL,
    phone_number VARCHAR(100)                             NULL,
    email        VARCHAR(100)                             NULL,
    private_key  VARCHAR(100)                             NOT NULL,
    public_key   VARCHAR(100)                             NOT NULL,
    version      INT         DEFAULT 0                    NOT NULL
);
COMMENT ON TABLE  wallet              IS 'Кошелек пользователя';
COMMENT ON COLUMN wallet.wallet_id    IS 'Идентификатор кошелька';
COMMENT ON COLUMN wallet.role         IS 'Роль пользователя';
COMMENT ON COLUMN wallet.first_name   IS 'Имя пользователя';
COMMENT ON COLUMN wallet.last_name    IS 'Фамилия пользователя';
COMMENT ON COLUMN wallet.middle_name  IS 'Отчество пользователя';
COMMENT ON COLUMN wallet.phone_number IS 'Номер пользователя';
COMMENT ON COLUMN wallet.email        IS 'Почта пользователя';
COMMENT ON COLUMN wallet.private_key  IS 'Приватный ключ пользователя';
COMMENT ON COLUMN wallet.public_key   IS 'Публичный ключ пользователя';
COMMENT ON COLUMN wallet.version      IS 'Версия кошелька';
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
    id         UUID    PRIMARY KEY,
    wallet_id  UUID    REFERENCES wallet (wallet_id)    ON DELETE CASCADE,
    team_id    UUID    REFERENCES teams (team_id)       ON DELETE CASCADE
);
--rollback DROP TABLE wallet_teams;

--changeset nkochnev:create-items
CREATE TABLE items
(
    item_id      UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    name         VARCHAR(100)                                        NOT NULL,
    image_uri    VARCHAR(255)                                        NOT NULL,
    description  VARCHAR(255)                                        NULL,
    coins        DECIMAL        DEFAULT 0.0                          NOT NULL,
    nft          INT            DEFAULT 0                            NOT NULL,
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
COMMENT ON COLUMN items.coins        IS 'Цена предмета в монетах';
COMMENT ON COLUMN items.nft          IS 'Цена предмета в сертификатах';
COMMENT ON COLUMN items.created_date IS 'Дата создания';
COMMENT ON COLUMN items.created_by   IS 'Кем создано';
COMMENT ON COLUMN items.updated_date IS 'Дата обновления';
COMMENT ON COLUMN items.updated_by   IS 'Кем обновлено';
--rollback DROP TABLE items;

--changeset nkochnev:create-actions
CREATE TABLE actions
(
    action_id             UUID          DEFAULT uuid_generate_v1()   PRIMARY KEY,
    name                  VARCHAR(100)  UNIQUE                       NOT NULL,
    description           VARCHAR(255)                               NULL,
    coins                 DECIMAL       DEFAULT 0                    NULL,
    matic                 DECIMAL       DEFAULT 0                    NULL,
    nft                   INT           DEFAULT 0                    NULL,
    role                  VARCHAR(50)                                NULL,
    can_be_changed_reward BOOLEAN       DEFAULT true                 NOT NULL,
    visible               BOOLEAN       DEFAULT true                 NOT NULL,
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
COMMENT ON COLUMN actions.coins                   IS 'Сумма операции в монетах';
COMMENT ON COLUMN actions.matic                   IS 'Сумма операции в MATIC';
COMMENT ON COLUMN actions.nft                     IS 'Сумма операции в сертификатах';
COMMENT ON COLUMN actions.role                    IS 'Необходимая роль для операции';
COMMENT ON COLUMN actions.visible                 IS 'Флаг для операций, невидимых пользователю';
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
    action_id              UUID                                         NOT NULL,
    hash                   VARCHAR(100)                                 NOT NULL,
    description            VARCHAR(255)                                 NULL,
    status                 VARCHAR(50)                                  NULL,
    coins                  DECIMAL        DEFAULT 0                     NULL,
    matic                  DECIMAL        DEFAULT 0                     NULL,
    token_id               INT            DEFAULT 0                     NULL,
    wallet_id              UUID                                         NOT NULL,
    created_date           TIMESTAMP      DEFAULT now()                 NOT NULL,
    transaction_initiator  UUID                                         NOT NULL
);
COMMENT ON TABLE  transaction_history                       IS 'Транзакции';
COMMENT ON COLUMN transaction_history.transaction_id        IS 'Идентификатор транзакции';
COMMENT ON COLUMN transaction_history.item_id               IS 'В случае обмена id предмета';
COMMENT ON COLUMN transaction_history.hash                  IS 'Хеш транзакции в блокчейне';
COMMENT ON COLUMN transaction_history.status                IS 'Статус транзакции в блокчейне, если null - не выполнена еще';
COMMENT ON COLUMN transaction_history.description           IS 'Пояснение транзакции, если необходимо';
COMMENT ON COLUMN transaction_history.coins                 IS 'Сумма в монетах';
COMMENT ON COLUMN transaction_history.matic                 IS 'Сумма в MATIC';
COMMENT ON COLUMN transaction_history.token_id              IS 'Id nft токена';
COMMENT ON COLUMN transaction_history.action_id             IS 'Id действия';
COMMENT ON COLUMN transaction_history.wallet_id             IS 'На кого проведена транзакция';
COMMENT ON COLUMN transaction_history.created_date          IS 'Дата создания';
COMMENT ON COLUMN transaction_history.transaction_initiator IS 'Инициатор транзакции';
--rollback DROP TABLE transaction_history;

--changeset nkochnev:create-triggers
CREATE TABLE triggers
(
    trigger_id   UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    name         VARCHAR(100)                                        NOT NULL,
    wallet_id    UUID                                                NULL,
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
COMMENT ON COLUMN triggers.wallet_id    IS 'id пользователя';
COMMENT ON COLUMN triggers.group_id     IS 'id команды';
COMMENT ON COLUMN triggers.trigger_type IS 'Тип триггера';
COMMENT ON COLUMN triggers.reward_time  IS 'Время награждения';
COMMENT ON COLUMN triggers.action_id    IS 'Id действия';
COMMENT ON COLUMN triggers.created_date IS 'Дата создания';
COMMENT ON COLUMN triggers.created_by   IS 'Кем создано';
COMMENT ON COLUMN triggers.updated_date IS 'Дата обновления';
COMMENT ON COLUMN triggers.updated_by   IS 'Кем обновлено';
--rollback DROP TABLE triggers;

--changeset nkochnev:create-news
CREATE TABLE news
(
    news_id      UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    name         VARCHAR(200)                                        NOT NULL,
    text         TEXT                                                NOT NULL,
    open_comm    BOOLEAN        DEFAULT false                        NOT NULL,
    created_date TIMESTAMP      DEFAULT now()                        NOT NULL,
    created_by   UUID                                                NOT NULL,
    updated_date TIMESTAMP                                           NOT NULL,
    updated_by   UUID                                                NOT NULL,
    version      INT            DEFAULT 0                            NOT NULL
);
COMMENT ON TABLE  news              IS 'Новость ';
COMMENT ON COLUMN news.news_id      IS 'Идентификатор новости';
COMMENT ON COLUMN news.name         IS 'Название новости';
COMMENT ON COLUMN news.text         IS 'Текст новости';
COMMENT ON COLUMN news.open_comm    IS 'Комментарии открыты';
COMMENT ON COLUMN news.created_date IS 'Дата создания';
COMMENT ON COLUMN news.created_by   IS 'Кем создано';
COMMENT ON COLUMN news.updated_date IS 'Дата обновления';
COMMENT ON COLUMN news.updated_by   IS 'Кем обновлено';
COMMENT ON COLUMN news.version      IS 'Версия новости';
--rollback DROP TABLE news;

--changeset nkochnev:create-comments
CREATE TABLE comments
(
    comment_id   UUID           DEFAULT uuid_generate_v1()           PRIMARY KEY,
    news_id      UUID           REFERENCES news(news_id)             ON DELETE CASCADE,
    text         TEXT                                                NOT NULL,
    edited       BOOLEAN        DEFAULT false                        NOT NULL,
    likes        INT            DEFAULT 0                            NOT NULL,
    created_date TIMESTAMP      DEFAULT now()                        NOT NULL,
    created_by   UUID                                                NOT NULL,
    updated_date TIMESTAMP                                           NOT NULL,
    updated_by   UUID                                                NOT NULL,
    version      INT            DEFAULT 0                            NOT NULL
);
COMMENT ON TABLE  comments              IS 'Комментарий';
COMMENT ON COLUMN comments.comment_id   IS 'Идентификатор комментарий';
COMMENT ON COLUMN comments.news_id      IS 'Id новости';
COMMENT ON COLUMN comments.text         IS 'Текст комментария';
COMMENT ON COLUMN comments.edited       IS 'Было ли исправление';
COMMENT ON COLUMN comments.likes        IS 'Кол-во благодарностей';
COMMENT ON COLUMN comments.created_date IS 'Дата создания';
COMMENT ON COLUMN comments.created_by   IS 'Кем создано';
COMMENT ON COLUMN comments.updated_date IS 'Дата обновления';
COMMENT ON COLUMN comments.updated_by   IS 'Кем обновлено';
COMMENT ON COLUMN comments.version      IS 'Версия комментария';
--rollback DROP TABLE comments;