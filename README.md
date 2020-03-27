# SpringBootCrudWithREST

Projede Mysql veri tabanı kullanılacağı için ilgili Mysql ile  konfigürasyonları application.properties içerisinde yapıldı.
application.yml dosyası içerisinde de log kaydı için gerekli ayarlar var.

Model paketi altındaki verilerimizin model sınıfımız var.Proje ayağa kaldırıldığı zaman hibernate bizim için tabloları ve diğer özellikleri bizim veri tabanında oluşturulyor.

jpa paketi altındki PersonRepositoryJpaImpl sınıfında Jpa nın Entity manager sınıfı kullanılarak crud işlemlerin yapıldığı sınıfımız mevcut.

Service paketi altında RestServiceImpl sınıfın var burada şimdilik bir ara katman görevi görmenin yanı sıra Transactional yönetimini yapılıyor.Spring boot da Sınıf üzerine 
@Transactional anatasyonunu koymamız yeterli.

Web paketi altındaki CrudRestController sınıfı is rest requestleri karşılandığı ve gelen responsların işlendiği sınıftır.

Security paketi altındaki SecurityConfiguration sınıfında ise Spring security ile ilgili ayarlar ve http basic auth özelliği kullanıarak basit bi yetkilendirme yapılmıştır.

