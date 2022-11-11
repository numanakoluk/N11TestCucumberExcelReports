Feature:n11 WEB kampanyalar sayfasında kampanya URL'lerini alma seneryosu

  @campaigns
  Scenario: Kampanyaları ve promosyon URL'lerini alın ve excel dosyasına aktarın
    Given Kullanici tarayiciyi acar kampanyalar sayfasina gider
    When Kullanici her bir kategoriye tek tek tıkladiğinda kampanyalar sayfasına gider
    Then Kullanıcı kategorilerdeki her kampanya URL ini excel dosyasına aktarır