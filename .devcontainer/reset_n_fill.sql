DROP TABLE IF EXISTS "public"."message_thread";
DROP TABLE IF EXISTS "public"."message";
DROP TABLE IF EXISTS "public"."thread";
DROP TABLE IF EXISTS "public"."user";

CREATE TABLE "public"."user" (
	"id" uuid NOT NULL,
	"name" varchar(50) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."message" (
    "id" uuid NOT NULL,
    "author_id" uuid NOT NULL,
    "content" text NOT NULL,
    "date" timestamp NOT NULL DEFAULT now(),
    CONSTRAINT "messages_author" FOREIGN KEY ("author_id") REFERENCES "public"."user"("id"),
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."thread" (
    "id" uuid NOT NULL,
    "label" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."message_thread" (
    "messages_id" uuid NOT NULL,
    "threads_id" uuid NOT NULL,
    CONSTRAINT "threads_messages" FOREIGN KEY ("threads_id") REFERENCES "public"."thread"("id"),
    CONSTRAINT "messages_threads" FOREIGN KEY ("messages_id") REFERENCES "public"."message"("id"),
    PRIMARY KEY ("messages_id","threads_id")
);

INSERT INTO "public"."user" ("id", "name")
VALUES
	('6d6ba1eb-3f9f-41b3-88af-fd3a959faeaa', 'Angelisium'),
	('e6d8b859-22ea-4d4d-a958-f7677a23a38b', 'Alice');

INSERT INTO "public"."thread" ("id", "label")
VALUES ('6c77bdf7-477d-4147-b5fc-b849021936be', 'Général');

INSERT INTO "public"."message" ("id", "author_id", "content", "date")
VALUES
	('ec7b4f6c-b1ce-4b3b-be8d-6b64b3b02424', '6d6ba1eb-3f9f-41b3-88af-fd3a959faeaa', 'Salut à tous, vous allez bien ?', '2023-08-02 09:30:00.000'),
	('73ea8b51-7f71-48e5-a12d-1651f281f28b', 'e6d8b859-22ea-4d4d-a958-f7677a23a38b', 'Super bien, c''est une super journée :p', '2023-08-02 09:31:00.000');

INSERT INTO "public"."message_thread" ("messages_id", "threads_id")
VALUES
	('ec7b4f6c-b1ce-4b3b-be8d-6b64b3b02424', '6c77bdf7-477d-4147-b5fc-b849021936be'),
	('73ea8b51-7f71-48e5-a12d-1651f281f28b', '6c77bdf7-477d-4147-b5fc-b849021936be');

-- eed88ace-ca60-45c6-b1df-474341353da8
-- bc71b955-c990-46ff-a9cd-34619b27371b
-- e486bf77-fdf3-4b0b-9e53-6894a97f56ec
-- 8c730491-aa05-4243-ae5f-6751a2b8e4c8
-- 26225409-476a-4eae-b7bf-360c5febf3ed
-- db5c3807-c5e8-4b02-8bbd-70b715081843
-- a466cfae-7b8c-4589-afe9-3f66a1708d07
-- 9a57c5a7-6da9-4ffe-a630-2bb461612c5f
-- 300e8438-7e80-40a1-b723-99ee86c146c3
-- d9f0ccfa-2937-43a2-bdbc-116bf58d5543
-- ee153774-60ac-474d-8f91-4eadc48224f1
-- bea85c64-a797-4a03-85af-40d6dc592a64
-- 0e1620f9-56b0-4cb3-9368-2e71da02bf23
-- de524cb8-1b65-4e3a-a06d-b3840100041e
-- bef76ebd-a6bc-4373-a167-ecb50b953740
-- d7c55a82-6058-444b-a090-762416b48e86
-- e4adf515-7329-417e-ace8-bf1b77a0b870
-- 3e377677-b36e-4eea-9044-be3dc5842b83
-- 6a9d37c6-4da6-45e4-bcf0-c9d74a3abb16
-- 1b32585b-1ef3-4d3b-9454-14136fde1483
-- 9017aea8-aeb7-468a-8710-35fc367eba50
-- 65455ea3-b637-458d-b061-4ae13ec4baf4
-- 55252f1d-14bb-4c83-932e-1b75dc842164
-- 76c2259f-bc0d-4b5b-81c4-77effcfe1b7d
-- 97d7bc62-2605-47d4-bf3c-776765326b28
-- aaf5f42d-ecab-4deb-a923-984aded22904
-- cfa3122a-9fae-40be-8ed4-3dcce0f981ca
-- af3860a2-d039-41f1-90bf-9f4daed1d809
-- 62aba669-c7d5-40ba-9428-c7ec39120ec3
-- 9d9f9690-baa6-4192-8d7e-b162eb6a15f2
-- 51385dfe-1998-4cb5-8d1b-131a48576aa3
-- 2afd62aa-1fe4-41ba-85ae-80646b664942
-- 67c8aa62-b50c-4825-bd0f-731561c74a66
-- 2f5c22e0-7b15-4dff-b6ab-ef549bdfee21
-- 30bc3319-2cd5-4a64-8981-e5e816a3a5f6
-- 7972ba97-56fd-4c9c-9a1b-bac04097749a
-- f86d31cc-3040-4854-9b1b-aeb42279ee8b
-- 25feddbd-04cb-4929-b882-3d5de7eeb4e1
-- 14dba447-8d14-4600-bd3d-3e3217ec2e45
-- 17a4c928-0d78-4ba0-bc20-b72b272880cc
-- 4130d1fe-6070-428b-a65b-238a24da35eb
-- a15525d2-938a-4817-9761-1b16d94d2917
-- 47eae7d7-d3aa-4aca-9241-ef7b4351618d
-- 0b8e915e-61bf-45e9-a419-f40b58d13fba
-- 737c6c9e-3efa-4ac4-ab28-cf31b1228163
-- dbcb8031-576e-4e37-8ce1-c8246d362ae4
-- 698f5c6c-71af-47d2-b690-f568334518cf
-- d19194ca-bcae-481d-982c-4d5bce2340fc
-- 1f66def2-fe8a-40e7-b18f-c08b7664ec32
-- c1106327-9820-442a-b671-b3dfd26c6dad
-- 2a1d20b6-3b62-4547-a1c7-d01b66e2b77b
-- 18668b66-8845-4acf-bc1b-b5f832a689cc
-- 5d1c59a9-760b-4abf-89eb-4dd4c29bd038
-- 4f5c9750-e911-4acf-9359-4f0fa23cca97
-- 00f0e9fb-b826-4e4e-a1ae-55de360b7d70
-- 4733aa68-132e-41b4-8691-f1881fc2cf9c
-- 03d7b22c-1670-49d4-ba7b-1c11c8ba7c73
-- 96dab506-b28e-4e67-8a64-3e74389e9589
-- 670d7e1d-9653-41dd-a9a8-bca87b2eb478
-- efbe996d-9805-41d1-850f-8dbc56a01cda
-- 77ebdf25-0ba2-4c8f-97dd-b0782b008d41
-- d863d15f-ce90-4cf6-8def-4d84c07915aa
-- a2c1bff5-1b79-40cc-9655-df4ed93d514b
-- bb69978a-191d-43a0-b72f-8a52116e5a11
-- 16da4e22-5b35-4a47-aa1b-8607c9659fbf
-- bc7de1a2-79d7-4462-aa72-e3192acc8de4
-- 304716aa-453c-4c3a-adaa-74124a44a891
-- 53593bad-18e8-4f7a-998f-0615dc91febe
-- 7bceaddd-20d1-417d-821c-939f3a558826
-- 12f8c53b-5519-40c2-89d6-b149283ab41c
-- 06024d58-3625-47f4-9ab1-6a6ee5cc75fa
-- 0da3b9d6-9645-436a-9b8d-961e4c3ec7d8
-- 266afe0f-b740-43f8-944a-37d832641ca9
-- a810450f-ddf1-400c-b748-e4ac6ace0182
-- 9f228b2e-3289-4818-b30f-686d629fe7b5
-- 7ac871f0-8b0e-49ef-8216-a7f8b18fe2ca
-- 60ee7305-82a8-4f1e-9608-04b275df3567
-- 6e3e9d29-d6c2-4a8e-9259-13a90eba63b2
-- bdb69174-a856-4465-85d0-253125cfc9d0
-- 61a5aaed-6804-4167-9d4d-0a5e40ff8ddc
-- 54aeb1fd-6617-4b21-9ab9-2a2b2eada110
-- d5340e80-3b03-41de-8b23-ca0bbadca942
-- 33bc0019-7b95-4506-ba15-2af2165f6ebf
-- 2e46abbc-bb70-478c-8452-0b2c57762acd
-- 013af589-923f-4e92-9158-9f2fe0643a66
-- 689e7a7d-947d-473d-a3ff-a2cb138fa01a
-- 832c7aaa-6f44-4812-bcba-5fc89df6eea5
-- f5a8e067-b902-4780-b1cf-1b31bb167d88
-- 3a645598-b865-4dde-9919-6f4bbd4d8e84
-- 5eaa67d2-df28-4bea-84cf-d87df1790b61
-- 60de7404-e817-43b8-9206-5ee0fb63368a
-- e1ece561-63d7-493d-a449-bacb10ed331c
-- 200e1eb3-15a4-4d57-bb3c-4a97a80e736f
-- e3db2e9c-3046-4304-8312-31e8b4f61c0c
-- 50649f41-4fd8-4615-8a2c-15e8b850d7d2
-- 2e3a2c9c-59e5-42a1-96f7-7ad46fcff3de
-- f8e93fd5-095f-4f30-9060-af6391bb0276
-- 11145856-f964-4470-9c2f-6c22933aa5fb
-- d5ed4c2f-e6bb-46aa-bdca-51add7103b3c
-- 81eb31eb-a6ee-4279-853d-620f720a56fa
-- 56f1d2b2-0fe1-4351-93ce-7c2dbc7258ef
-- 2491563c-ce1a-4ee2-b0cd-8f2229eb357b
-- 8d77fb74-0488-48d7-a077-8382ff5ed947
-- a5717646-eb5e-4da3-bdbf-49bb5c8a97f1
-- bb5e22cd-96a4-4735-b60f-a96b8588a46c
-- cbecf179-1d78-48c1-9cb2-1fbe01f16a72
-- 1cb94916-d96a-42d8-9970-0c38c35ff6a4
-- aeee934f-5768-4a97-9c90-24b05c56a3f6
-- 1096713a-91c8-45de-8996-2b309d41e5b8
-- c817c639-f28a-4d57-817d-477e655bd215
-- 7d335ef8-e140-4e97-ac50-68db169977d7
-- 0e25f112-9662-466a-8223-71dff13a70d2
-- db454eef-f853-418a-bc32-002f0ab0103a
-- e4c215b0-2e59-461e-90f2-61de0a120cb1
-- e015b0bd-abda-47f9-bd91-7db9860eb506
-- 9f8d1a50-1193-4531-87d2-9fb6f341b5e4
-- 8da1f942-5c8d-457c-8175-317dcf0a3faa
-- 15fa097d-db30-4cd8-85d2-876f9960088c
-- 30d4d145-92e1-40cb-b785-6236d256bd7d
-- 01b70a7b-ca1d-4620-afb8-f7fe88178bbf
-- fb091bf9-b58d-4501-9975-e5f8a53f81f2
-- 05a09abb-a453-45fc-b559-00db924fdc80
-- f11de419-d597-422a-816d-a37f3e685954
-- 21acbf32-9b4a-45b4-ab21-cc05e946baab
-- d6105749-9bbb-403f-b9ae-0a325c0e67bf
-- 5f033eb8-5b6e-4495-8eca-4e30861ce493
-- 41d00526-d9c6-404c-adfd-c2e8856e47c1
-- ae9bc70f-7104-475e-a4f1-fe3019493a25
-- a3bbea89-31b1-47eb-afb8-73a120872ebf
-- c6221932-acde-422c-a590-7c441015f32e
-- 10861d17-0de5-4242-9335-bc66661ff186
-- 7a4ec5ed-c6a1-4182-ac2a-fbc6ca16122f
-- 6c11058e-9e88-4b0e-b838-0e20a0661802
-- 07366945-e316-4b49-88d8-efc8ce40e7a7
-- 8d31c810-cd31-4576-95c5-d8cd857296a6
-- 49c3bcc0-12ea-414f-801f-5acaea2875ee
-- 7811e383-cd14-4436-a65d-c629665fa2e4
-- 2827dc4b-cdda-431b-bcc0-da0b08fc635e
-- ef622a89-acf8-4b88-b1db-f3b3116af7ae
-- 66288ce1-c4af-4942-b9f9-02bfadb96964
-- f45c511e-4440-401b-852b-cedf615eb643
-- 84537b42-fe31-4e7c-8004-28bb3b555474
-- 956bc5e7-0f4c-44c4-8bcd-121650ba5e5d
-- 19ff28e9-a2aa-420d-948d-6c80481161fe
-- e73bd38e-537f-49b3-bcb6-add60e68d70f
-- 16dfcc0c-e00f-4c6b-87c7-0dafde1963ba
-- b96bc6a2-d19a-4bc0-a1f1-bb96c67cedcb
-- d7c9649b-c6f2-4721-9b67-60e898c7dc31
-- 6ae4a67f-1d18-4dfc-a7d9-2fe3fce422da
-- f5e4051e-78b6-4524-8af3-06031722803c
-- 3b9b6679-409c-4075-8cb8-761324b70dd4
-- 377b9bf9-be2f-49e8-8e7d-093633596cbf
-- 81fcec09-91e9-4d29-9be6-96eb4fc9583b
-- 935b5ab3-b686-4eed-92fb-32bc9c17cf44
-- 1d902ca4-f72d-4823-8ebb-06f4abf0f81a
-- 8a8455a1-ae07-4b42-ad37-2c2d6e1c9a51
-- fc7a0ec9-d215-41f5-9d0d-efb847d7cd66
-- 50a5240b-9527-4f88-9b15-7aa75b1cc76e
-- 3edbc648-b43e-4da8-ab01-553d78af5247
-- 5796f072-6137-4b40-bb24-9057daa9f32b
-- 3b873387-2fcd-40ba-b5bc-011bf856619b
-- 66db1567-1c67-4232-9da3-c2c8b6d56672
-- e4901e0a-c20b-496d-8110-e874bd779ff9
-- a6ea340e-f38a-42b3-9f77-423b28a7e507
-- f36833e6-b90f-4410-8048-6c4636ea690c
-- e8ab0020-d5a4-47d6-839f-b51076f33e85
-- 0f1ebcf0-a944-494f-976b-e4d5f66806c0
-- a49e6fe3-2381-4f87-b70d-7f7d3422012c
-- 9173de88-f6a8-4430-9363-12bc56d3aff6
-- 1252156e-0e7c-422b-a3bb-1a38688a112d
-- 85ce3ca4-e04f-43b3-a0fd-7416e5307419
-- b2f1260b-7140-465d-bbcd-b5faec05c175
-- 03fbcd76-8c5c-4457-a4cc-cdacd79bb798
-- e1be81c3-893c-442e-97d2-913f7717adf5
-- fda9a8b8-3fd8-4e47-8bed-9a33fafa408c
-- ad97db1d-3147-4784-9b2c-024ccceaabe7
-- 2653f93b-b522-4ef5-a3c0-5aa70fc373c3
-- 634d3378-894b-44de-ae89-488b6fe1f922
-- 06c154e6-d7e7-4744-bfaf-1a5f3ae4af6e
-- 35dae7ef-9401-4f23-a206-f2d740ae0e31
-- e340c4e0-de0b-42f1-a80a-f8ae3d22f259
-- 272daf7c-908e-4060-a711-8ea00cadb883
-- 7b8c4bec-bd25-4940-be8e-d2c8834e08c2
-- 91e9e2b4-fa76-42eb-a728-8060897a432b
-- 86726a2d-dba3-4874-a92a-383f8d46468f
-- 660df444-d0b7-4d02-8fef-2dd34c110216
-- b324881f-fa28-418f-8710-2e23b763ec92
-- 587d5667-2248-46f3-b27e-118fdc20cf32
-- 8e2e6427-85d2-41db-89e3-594518f4d215
-- 0e0cfaa7-97c9-43a7-87b4-a27224979a29
-- 024d5cd7-3836-4552-92ff-1e7a005afcc6
-- 1e64434d-68be-4a6d-a819-c9240d479e90
-- 82b637a2-947c-49e2-807b-d8f7022c1c7f
-- b0dda15c-2561-4b7c-8c79-1f5fa5a934e1
-- 0247586a-86c2-4788-ac8e-8545fb57ed4c
-- 01630df9-c8f0-4b3e-a8ac-21f29e2a8870