-- liquibase formatted sql

--changeset nkoechnev:create-deafult-team
INSERT INTO teams (name, team_type) VALUES ('ВТБ', 'COMPANY');
--rollback DELETE FROM teams WHERE name = 'ВТБ'

--changeset nkoechnev:seed-actions
INSERT INTO actions (name, coins, operation_type, created_by, updated_date, updated_by) VALUES
('Прохождение блока внутренних курсов', 5, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000'),
('Подготовить доклад на внутренний митап', 10, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000'),
('Подготовить статью на Habr', 15, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000'),
('Участие в IT подкасте ', 15, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000'),
('Принятое улучшение процессов компании', 25, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000'),
('Прохождение блока внутренних курсов', 5, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000'),
('Участие в профильной конференции', 30, 'INCREASE', '00000000-0000-0000-0000-000000000000', now(), '00000000-0000-0000-0000-000000000000')
--rollback DELETE FROM actions WHERE created_by = '00000000-0000-0000-0000-000000000000';