# Veterinary Management System

## Project Description

The Veterinary Management System is an API designed to help veterinary clinics manage their operations efficiently. This system provides various features to manage veterinary doctors, available days, customers, animals, vaccines, and appointments. Users can create appointments for animals, manage vaccine records, and more.

## Features

- **Veterinary Doctor Management**: Add, update, view, and delete doctors.
- **Doctor Availability Management**: Add, update, view, and delete doctor's available days.
- **Animal and Customer Management**: Add, update, view, and delete animals and customers.
- **Vaccine Management**: Add, update, view, and delete vaccines. Check vaccine protection end dates.
- **Appointment Management**: Create, update, view, and delete appointments for animals. Check doctor availability.

## Requirements

- JDK 17 or newer
- PostgreSQL
- Maven

## API Endpoints

### Animal and Customer Relations

- @ManyToOne and @OneToMany between Animal and Customer
- @ManyToMany between Animal and Vaccine
- @OneToMany between Animal and Appointment
- @ManyToOne between Appointment and Doctor
- @ManyToOne between AvailableDate and Doctor
- @OneToMany between Doctor and Appointment, AvailableDate

### Animals

- **Add Animal**: `POST /v2/animals`
- **Update Animal**: `PUT /v2/animals`
- **Delete Animal**: `DELETE /v2/animals/{id}`
- **Filter Animals by Name**: `GET /v2/animals/name/{name}`
- **View Customer's Animals**: `GET /v2/animals/customer/{customerId}`

### Customers

- **Add Customer**: `POST /v2/customers`
- **Update Customer**: `PUT /v2/customers`
- **Delete Customer**: `DELETE /v2/customers/{id}`
- **Filter Customers by Name**: `GET /v2/customers/{name}`

### Vaccines

- **Add Vaccine**: `POST /v2/vaccines`
- **Update Vaccine**: `PUT /v2/vaccines`
- **View Vaccine**: `GET /v2/vaccines/{id}`
- **Delete Vaccine**: `DELETE /v2/vaccines/{id}`
- **View Animal's Vaccines**: `GET /v2/animals/{animalId}/vaccines`
- **List Vaccines Approaching End Date**: `GET /v2/vaccines/filter?startDate={startDate}&endDate={endDate}`

### Appointments

- **Create Appointment**: `POST /v2/appointments`
- **Update Appointment**: `PUT /v2/appointments`
- **View Appointment**: `GET /v2/appointments/{id}`
- **Delete Appointment**: `DELETE /v2/appointments/{id}`
- **Filter Appointments by Doctor and Date Range**: `GET /v2/appointments/doctor/{doctorId}?startDate={startDate}&endDate={endDate}`
- **Filter Appointments by Animal and Date Range**: `GET /v2/appointments/animal/{animalId}?startDate={startDate}&endDate={endDate}`

### Doctors

- **Add Doctor**: `POST /v2/doctors`
- **Update Doctor**: `PUT /v2/doctors`
- **View Doctor**: `GET /v2/doctors/{id}`
- **Delete Doctor**: `DELETE /v2/doctors/{id}`

### Doctor's Available Days

- **Add Available Day**: `POST /v2/doctors/{doctorId}/availableDates`
- **Update Available Day**: `PUT /v2/doctors/{doctorId}/availableDates`
- **View Available Days**: `GET /v2/doctors/{doctorId}/availableDates`
- **Delete Available Day**: `DELETE /v2/doctors/{doctorId}/availableDates`

## UML Diagram

![UML Diagram](https://github.com/user-attachments/assets/c3f70b82-ef3f-45e2-a1ee-54d00a3d46b2)


