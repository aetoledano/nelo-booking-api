-- restaurants
insert into restaurants (id, name, two_seat_table_capacity, four_seat_table_capacity, six_seat_table_capacity)
values ('4d62cf3f-d855-4c38-b6bc-e0defb6cfb74', 'Lardo', 4, 2, 1);

insert into restaurants (id, name, two_seat_table_capacity, four_seat_table_capacity, six_seat_table_capacity)
values ('ab4f519a-edba-4674-adee-fc116c7a362d', 'Panadería Rosetta', 3, 2, 0);

insert into restaurants (id, name, two_seat_table_capacity, four_seat_table_capacity, six_seat_table_capacity)
values ('d873242a-7dcf-4f8b-8be9-88a4a037ed60', 'Tetetlán', 4, 2, 1);

insert into restaurants (id, name, two_seat_table_capacity, four_seat_table_capacity, six_seat_table_capacity)
values ('c6845b9c-79c8-4ceb-9ecf-358c2e49a633', 'Falling Piano Brewing Co', 5, 5, 5);

insert into restaurants (id, name, two_seat_table_capacity, four_seat_table_capacity, six_seat_table_capacity)
values ('3b6155eb-5dc3-4faf-a07e-8093c13f8f3d', 'u.to.pi.a', 2, 0, 0);

-- dietary groups
insert into dietary_groups (id, name)
values (1, 'VEGETARIAN');

insert into dietary_groups (id, name)
values (2, 'GLUTEN_FREE');

insert into dietary_groups (id, name)
values (3, 'PALEO');

insert into dietary_groups (id, name)
values (4, 'VEGAN');

-- lardo food options
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('4d62cf3f-d855-4c38-b6bc-e0defb6cfb74', 2);

-- panaderia rosetta food options
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('ab4f519a-edba-4674-adee-fc116c7a362d', 1);
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('ab4f519a-edba-4674-adee-fc116c7a362d', 2);

-- tetetlan food options
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('d873242a-7dcf-4f8b-8be9-88a4a037ed60', 2);
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('d873242a-7dcf-4f8b-8be9-88a4a037ed60', 3);

-- utopia food options
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('3b6155eb-5dc3-4faf-a07e-8093c13f8f3d', 1);
insert into restaurant_dietary_group (restaurant_id, dietary_group_id)
values ('3b6155eb-5dc3-4faf-a07e-8093c13f8f3d', 4);

-- clients
insert into clients (id, name)
values ('f8da5142-8457-4267-8fe9-cb154acc4f4c', 'Paulina');

insert into clients (id, name)
values ('96fd7942-5ede-4c66-8e9e-cd44fdbd6367', 'Michael');

insert into clients (id, name)
values ('dcda261d-47d9-4462-8541-7faee7277be4', 'George Michael');

insert into clients (id, name)
values ('bc7181c7-5a62-43de-a7fd-e2f1dfd1816e', 'Lucile');

insert into clients (id, name)
values ('84c13d42-933d-429b-adf2-26aa6fda32d8', 'Gob');

insert into clients (id, name)
values ('d469f163-164e-4145-9567-f4943b83d5da', 'Tobias');

insert into clients (id, name)
values ('687374ce-2df5-4b60-ba7c-a7da6311bbeb', 'Maeby');

-- paulina food restrictions
insert into client_dietary_group (client_id, dietary_group_id)
values ('f8da5142-8457-4267-8fe9-cb154acc4f4c', 4);

-- michael food restrictions
insert into client_dietary_group (client_id, dietary_group_id)
values ('96fd7942-5ede-4c66-8e9e-cd44fdbd6367', 1);

-- george food restrictions
insert into client_dietary_group (client_id, dietary_group_id)
values ('dcda261d-47d9-4462-8541-7faee7277be4', 1);

insert into client_dietary_group (client_id, dietary_group_id)
values ('dcda261d-47d9-4462-8541-7faee7277be4', 2);

-- lucile food restrictions
insert into client_dietary_group (client_id, dietary_group_id)
values ('bc7181c7-5a62-43de-a7fd-e2f1dfd1816e', 2);

-- gob food restrictions
insert into client_dietary_group (client_id, dietary_group_id)
values ('84c13d42-933d-429b-adf2-26aa6fda32d8', 3);

-- maeby food restrictions
insert into client_dietary_group (client_id, dietary_group_id)
values ('687374ce-2df5-4b60-ba7c-a7da6311bbeb', 4);
