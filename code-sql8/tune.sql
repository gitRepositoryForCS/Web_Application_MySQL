/* time used with indexes: 1886ms.
*   time used without indexes: 5544ms.
*/


/* this index is created for the query of count number of patients in a specific zipcode. */
create index index_zipCode ON Patient(zipcode);

/* this index is created for the query of count number of patients within an age range. */
create index index_age on Patient(age);

/* this index is created for the query of checking how many patients have a certain disease. */
create index index_disease on Disease(disease);

/* this index is created for the query of looking up a doctor with a given specialty. */
create index index_specialty on Doctor(specialty);

/* this index is created for the query of looking up all patients for a doctor. */
create index index_patientName on Patient(fname,lname);

/* this index is created for the query of looking up all doctors for a patient. */
create index index_doctorName on Doctor(fname,lname);

