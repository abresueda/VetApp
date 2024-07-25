Veteriner Yönetim Sistemi

Proje Açıklaması

Veteriner Yönetim Sistemi, veteriner kliniklerinin işlerini yönetmelerine olanak tanıyan bir API'dir. Bu sistem, veteriner doktorları, çalışma günleri, müşteriler, hayvanlar, aşılar ve randevuları yönetmek için çeşitli özellikler sunar. Kullanıcılar, hayvanlar için randevu oluşturabilir, aşı kayıtlarını yönetebilir ve daha fazlasını yapabilir.

Özellikler

- **Veteriner Doktor Yönetimi**: Doktorların kaydedilmesi, güncellenmesi, görüntülenmesi ve silinmesi.
- **Doktor Müsait Günleri Yönetimi**: Doktorların müsait günlerini ekleme, güncelleme, görüntüleme ve silme.
- **Hayvan ve Müşteri Yönetimi**: Hayvanların ve müşterilerin kaydedilmesi, güncellenmesi, görüntülenmesi ve silinmesi.
- **Aşı Yönetimi**: Aşıların kaydedilmesi, güncellenmesi, görüntülenmesi ve silinmesi. Aşı koruyuculuk bitiş tarihi kontrolü.
- **Randevu Yönetimi**: Hayvanlar için randevu oluşturma, güncelleme, görüntüleme ve silme. Doktorun müsait gün ve saat kontrolü.

Gereksinimler

- JDK 17 veya daha yeni bir sürüm
- PostgreSQL
- Maven


API Endoints


![UML Diyagramı-VetApp](https://github.com/user-attachments/assets/ffe71ab5-871b-44f6-bf5b-1028011c3f60)






-Animal ve Customer arasında @ManyToOne ve @OneToMany ilişkisi
-Animal ve Vaccine arasında @ManyToMany ilişkisi
-Animal ve Appointment arasında @OneToMany ilişkisi
-Appointment ve Doctor arasında @ManyToOne ilişkisi
-AvailableDate ve Doctor arasında @ManyToOne ilişkisi
-Doctor ve Appointment, AvailableDate arasında @OneToMany ilişkisi

Hayvanlar
Hayvan Kaydetme                                                 --> POST /v2/animals
Hayvan Güncelleme                                           --> PUT /v2/animals
Hayvan Silme                                                         --> DELETE /v2/animals/{id}
Hayvanları İsmine Göre Filtreleme               --> GET /v2/animals/name/{name}
Müşterinin Hayvanlarını Görüntüleme       --> GET /v2/animals/customer/{customerId}

Müşteriler
Müşteri Kaydetme                                                --> POST /v2/customers
Müşteri Güncelleme                                           --> PUT /v2/customers
Müşteri Silme                                                         --> DELETE /v2/customers/{id}
Müşteri İsmine Göre Filtreleme                     --> GET /v2/customers/{name}

Aşılar
Aşı Kaydetme                                                         -->  POST /v2/vaccines
Aşı Güncelleme                                                    --> PUT /v2/vaccines
Aşı Görüntüleme                                                 ---> GET /v2/vaccines/{id}
Aşı Silme                                                                  --> DELETE /v2/vaccines/{id}
Hayvanın Aşılarını Görüntüleme                  --> GET /v2/animals/{animalId}/vaccines
Koruyuculuk Bitiş Tarihi Yaklaşan Aşıları 
Listeleme                                  --> GET /v2/vaccines/filter?startDate={startDate}&endDate={endDate}

Randevular
Randevu Oluşturma                                  --> POST /v2/appointments
Randevu Güncelleme                              --> PUT /v2/appointments
Randevu Görüntüleme                                 --> GET /v2/appointments/{id}
Randevu Silme                                                 --> DELETE /v2/appointments/{id}
Doktora Göre ve Tarih Aralığına Göre Randevu Filtreleme --> 
GET /v2/appointments/doctor/{doctorId}?startDate={startDate}&endDate={endDate}
Hayvana Göre ve Tarih Aralığına Göre Randevu Filtreleme --> 
GET /v2/appointments/animal/{animalId}?startDate={startDate}&endDate={endDate}

Doktorlar
Doktor Kaydetme                                                --> POST /v2/doctors
Doktor Güncelleme                                            --> PUT /v2/doctors
Doktor Görüntüleme                                         --> GET /v2/doctors/{id}
Doktor Silme                                                         --> DELETE /v2/doctors/{id}

Doktorun Müsait Günleri
Müsait Gün Ekleme                                             --> POST /v2/doctors/{doctorId}/availableDates
Müsait Gün Güncelleme                                  --> PUT /v2/doctors/{doctorId}/availableDates
Müsait Gün Görüntüleme                                --> GET /v2/doctors/{doctorId}/availableDates
Müsait Gün Silme                                                 --> DELETE /v2/doctors/{doctorId}/availableDates
