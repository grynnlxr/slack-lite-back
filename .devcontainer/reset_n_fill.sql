DROP TABLE IF EXISTS "public"."message";
DROP TABLE IF EXISTS "public"."thread";
DROP TABLE IF EXISTS "public"."user";

CREATE TABLE "public"."user" (
	"id" uuid NOT NULL,
	"name" varchar(50) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."thread" (
    "id" uuid NOT NULL,
    "label" varchar(255) NOT NULL,
	"locked" boolean NOT NULL DEFAULT false,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."message" (
    "id" uuid NOT NULL,
    "author_id" uuid NOT NULL,
	"thread_id" uuid NOT NULL REFERENCES "public"."thread" ON DELETE CASCADE,
    "content" text NOT NULL,
    "date" timestamp NOT NULL DEFAULT now(),
    CONSTRAINT "messages_author" FOREIGN KEY ("author_id") REFERENCES "public"."user"("id"),
    CONSTRAINT "messages_thread" FOREIGN KEY ("thread_id") REFERENCES "public"."thread"("id"),
    PRIMARY KEY ("id")
);

INSERT INTO "public"."user" ("id", "name")
VALUES
	('6d6ba1eb-3f9f-41b3-88af-fd3a959faeaa', 'Angelisium'),
	('e6d8b859-22ea-4d4d-a958-f7677a23a38b', 'Alice');

INSERT INTO "public"."thread" ("id", "label", "locked")
VALUES ('6c77bdf7-477d-4147-b5fc-b849021936be', 'Général', false);
-- VALUES ('6c77bdf7-477d-4147-b5fc-b849021936be', 'Général', true);

INSERT INTO "public"."message" ("id", "author_id", "thread_id", "content", "date")
VALUES (
	'ec7b4f6c-b1ce-4b3b-be8d-6b64b3b02424',
	'6d6ba1eb-3f9f-41b3-88af-fd3a959faeaa',
	'6c77bdf7-477d-4147-b5fc-b849021936be',
	'Salut à tous, vous allez bien ?',
	'2023-08-02 09:30:00.000'
),(
	'73ea8b51-7f71-48e5-a12d-1651f281f28b',
	'e6d8b859-22ea-4d4d-a958-f7677a23a38b',
	'6c77bdf7-477d-4147-b5fc-b849021936be',
	'Super bien, c''est une super journée :p',
	'2023-08-02 09:31:00.000'
);