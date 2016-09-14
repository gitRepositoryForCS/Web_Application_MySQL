/* drop indexes if they already exist */

drop INDEX index_zipCode ON Patient(zipcode);
drop index index_age on Patient(age);
drop index index_disease on Disease(disease);
drop index index_specialty on Doctor(specialty);
drop index index_patientName on Patient(fname,lname);
drop index index_doctorName on Doctor(fname,lname);
