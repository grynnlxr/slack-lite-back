DROP TABLE IF EXISTS "public"."message";
DROP TABLE IF EXISTS "public"."thread";

CREATE TABLE "public"."message" (
    "id" uuid NOT NULL,
    "author" varchar(50) NOT NULL,
    "content" text NOT NULL,
    "date" timestamp,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."thread" (
    "id" uuid NOT NULL,
    "label" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "public"."thread" ("id", "label")
VALUES ('6c77bdf7-477d-4147-b5fc-b849021936be', 'Général');