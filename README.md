Veteriner Yönetim Sistemi

Proje Açıklaması

Veteriner Yönetim Sistemi, veteriner kliniklerinin işlerini yönetmelerine olanak tanıyan bir API'dir. Bu sistem, veteriner doktorları, çalışma günleri, müşteriler, hayvanlar, aşılar ve randevuları yönetmek için çeşitli özellikler sunar. Kullanıcılar, hayvanlar için randevu oluşturabilir, aşı kayıtlarını yönetebilir ve daha fazlasını yapabilir.

Özellikler

- **Veteriner Doktor Yönetimi**: Doktorların kaydedilmesi, güncellenmesi, görüntülenmesi ve silinmesi.
- **Doktor Müsait Günleri Yönetimi**: Doktorların müsait günlerini ekleme, güncelleme, görüntüleme ve silme.
- **Hayvan ve Müşteri Yönetimi**: Hayvanların ve müşterilerin kaydedilmesi, güncellenmesi, görüntülenmesi ve silinmesi.
- **Aşı Yönetimi**: Aşıların kaydedilmesi, güncellenmesi, görüntülenmesi ve silinmesi. Aşı koruyuculuk bitiş tarihi kontrolü.
- **Randevu Yönetimi**: Hayvanlar için randevu oluşturma, güncelleme, görüntüleme ve silme. Doktorun müsait gün ve saat kontrolü.

Kurulum

Gereksinimler

- JDK 17 veya daha yeni bir sürüm
- PostgreSQL
- Maven


API Endoints
![UML Diyagramı-VetApp](https://github.com/user-attachments/assets/ffe71ab5-871b-44f6-bf5b-1028011c3f60)


Hayvanlar
Hayvan Kaydetme                     --> POST /v2/animals
Hayvan Güncelleme                   --> PUT /v2/animals
Hayvan Silme                        --> DELETE /v2/animals/{id}
Hayvanları İsmine Göre Filtreleme   --> GET /v2/animals/name/{name}
Müşterinin Hayvanlarını Görüntüleme --> GET /v2/animals/customer/{customerId}

