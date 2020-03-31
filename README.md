# SpringBootCrudWithREST

Projede Mysql veri tabanı kullanılacağı için ilgili Mysql ile  konfigürasyonları application.properties içerisinde yapıldı.
application.yml dosyası içerisinde de log kaydı için gerekli ayarlar var.

Model paketi altındaki verilerimizin model sınıfımız var.Proje ayağa kaldırıldığı zaman hibernate bizim için tabloları ve diğer özellikleri bizim veri tabanında oluşturulyor.

jpa paketi altındki PersonRepositoryJpaImpl sınıfında Jpa nın Entity manager sınıfı kullanılarak crud işlemlerin yapıldığı sınıfımız mevcut.

Service paketi altında RestServiceImpl sınıfın var burada şimdilik bir ara katman görevi görmenin yanı sıra Transactional yönetimini yapılıyor.Spring boot da Sınıf üzerine 
@Transactional anatasyonunu koymamız yeterli.

Web paketi altındaki CrudRestController sınıfı is rest requestleri karşılandığı ve gelen responsların işlendiği sınıftır.

Security paketi altındaki SecurityConfiguration sınıfında ise Spring security ile ilgili ayarlar ve http basic auth özelliği kullanıarak basit bi yetkilendirme yapılmıştır.

Create update delete işlemleri ve aynı zamanda tüm kayıtlarla erişim için sorgu,aynı soyadına sahip kişilere yönelik sorgu ve id ile sorgu işlemleri mevcuttur

JWT Authentication eklenmesi

http://localhost:8080/managers/sign-up path'ine atılan post istekleri herhangi bir authentication'a takılmadan kullanıcı kaydı yapılıyor bunu SecurityCongiguration
sınıfı içerisinde http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll(); komutu ile sağlamış olduk. Böylece kullanıcı
kaydı başarılı bi şekilde sağlamış oldu.Kayıt işlemi bittikten sonra username ve password bilgileri ile http://localhost:8080/login adresine yapacağımız post requestiyle
login işlemi gerçekleşmiş olacak ve bu işlemi sonunda bize bi token dönmüş olacak.Biz bu tokeni kullanarak yetki alıp ve rest service bağlanıp crud işlemlerini gerçekleştirebileceiz.
Burada login path'ini JWTAuthenticationFilter sınıfı UsernamePasswordAuthenticationFilter sınıfını implemente ediyor ve  bu sınıf dinamik olarak /login isteğini gerçekleştirmiş oluyor 
