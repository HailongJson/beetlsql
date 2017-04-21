select
===
select * from user where 1=1
@if(!isEmpty(id)) {
    and id = #id#
@}
@if(!isEmpty(name)) {
    and name = #name#
@}
@if(!isEmpty(age)) {
    and age = #age#
@}