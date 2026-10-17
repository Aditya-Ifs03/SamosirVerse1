<footer class="footer-wrapper">
  <div class="container">
    <div class="footer-top">
      <!-- Col 1: Brand & Philosophy -->
      <div class="footer-col-brand">
        <div class="footer-brand">
          <span class="footer-symbol">🌿</span>
          <span class="footer-brand-name">Samosir<span class="text-terracotta">Verse</span></span>
        </div>
        <p class="footer-desc">
          Platform ekosistem pariwisata cerdas, katalog warisan budaya Batak Toba, dan direktori kreator lokal Pulau Samosir. Dibangun dengan penuh rasa hormat terhadap kelestarian alam dan kearifan leluhur.
        </p>
        <div class="philosophy-card">
          <span class="philosophy-title">Falsafah Dalihan Na Tolu</span>
          <p class="philosophy-quote">"Somba marhula-hula, manat mardongan tubu, elek marboru."</p>
          <span class="philosophy-trans">Hormat kepada hula-hula, bijaksana kepada teman semarga, dan mengayomi anak/saudara perempuan.</span>
        </div>
      </div>

      <!-- Col 2: Navigasi Cepat -->
      <div class="footer-col">
        <h4 class="footer-heading">Eksplorasi</h4>
        <ul class="footer-list">
          <li><a href="/destinasi">Katalog Destinasi Wisata</a></li>
          <li><a href="/peta">Peta Interaktif Geopark Samosir</a></li>
          <li><a href="/trip-planner">Smart Trip Planner</a></li>
          <li><a href="/budaya">Cerita & Falsafah Budaya</a></li>
          <li><a href="/kreator">Direktori Maestro & UMKM</a></li>
          <li><a href="/agenda">Kalender Agenda & Festival</a></li>
        </ul>
      </div>

      <!-- Col 3: Wilayah Samosir -->
      <div class="footer-col">
        <h4 class="footer-heading">Wilayah Samosir</h4>
        <ul class="footer-list">
          <li><a href="/destinasi?subDistrict=Simanindo">Kecamatan Simanindo (Tomok, Tuk-Tuk)</a></li>
          <li><a href="/destinasi?subDistrict=Pangururan">Kecamatan Pangururan (Pusat Seni & Tenun)</a></li>
          <li><a href="/destinasi?subDistrict=Harian">Kecamatan Harian (Tele & Air Terjun Efrata)</a></li>
          <li><a href="/destinasi?subDistrict=Sitio-tio">Kecamatan Sitio-tio (Bukit Holbung)</a></li>
          <li><a href="/destinasi?subDistrict=Onan Runggu">Kecamatan Onan Runggu</a></li>
          <li><a href="/destinasi?subDistrict=Palipi">Kecamatan Palipi & Nainggolan</a></li>
        </ul>
      </div>

      <!-- Col 4: Creative Hub & Kontak -->
      <div class="footer-col">
        <h4 class="footer-heading">Inisiatif</h4>
        <div class="initiative-badge">
          <span class="badge-tag">Kompetisi Resmi</span>
          <p class="initiative-title">Creative Hub for App Talent Samosir 2026</p>
          <p class="initiative-sub">Karya inovasi teknologi digital untuk kemajuan pariwisata dan pemberdayaan perajin lokal Samosir.</p>
        </div>
        <div class="footer-contact">
          <span class="contact-label">Bantuan & Kemitraan Kreator:</span>
          <a href="https://wa.me/6281260819451?text=Halo%20SamosirVerse%2C%20saya%20tertarik%20bermitra" target="_blank" rel="noopener noreferrer" class="footer-wa-link">
            <span>💬 Hubungi Tim via WhatsApp</span>
          </a>
        </div>
      </div>
    </div>

    <div class="footer-bottom">
      <p class="footer-copy">
        &copy; 2026 SamosirVerse. Seluruh hak cipta dilindungi undang-undang. Dipersembahkan untuk Pulau Samosir & Danau Toba.
      </p>
      <div class="footer-bottom-links">
        <a href="/admin/login">Portal Administrator</a>
        <span>&bull;</span>
        <a href="https://github.com/Aditya-Ifs03/SamosirVerse1.git" target="_blank" rel="noopener noreferrer">Repositori GitHub</a>
      </div>
    </div>
  </div>
</footer>

<style>
  .footer-wrapper {
    background-color: var(--color-forest-dark);
    color: var(--color-ivory);
    padding: 4.5rem 0 2rem;
    margin-top: 4rem;
    border-top: 3px solid var(--color-terracotta);
  }

  .footer-top {
    display: grid;
    grid-template-columns: 2fr 1fr 1.2fr 1.3fr;
    gap: 3rem;
    margin-bottom: 3.5rem;
  }

  .footer-brand {
    display: flex;
    align-items: center;
    gap: 0.6rem;
    margin-bottom: 1rem;
  }

  .footer-symbol {
    font-size: 1.6rem;
  }

  .footer-brand-name {
    font-family: var(--font-serif);
    font-size: 1.6rem;
    font-weight: 700;
    color: var(--color-ivory);
  }

  .text-terracotta {
    color: var(--color-terracotta);
  }

  .footer-desc {
    color: rgba(246, 241, 231, 0.75);
    font-size: 0.88rem;
    line-height: 1.6;
    margin-bottom: 1.5rem;
  }

  .philosophy-card {
    background: rgba(255, 255, 255, 0.05);
    border: 1px solid rgba(255, 255, 255, 0.1);
    border-radius: var(--radius-md);
    padding: 1rem 1.25rem;
  }

  .philosophy-title {
    display: block;
    font-size: 0.72rem;
    text-transform: uppercase;
    letter-spacing: 0.08em;
    color: var(--color-lake);
    font-weight: 600;
    margin-bottom: 0.35rem;
  }

  .philosophy-quote {
    font-family: var(--font-serif);
    font-size: 0.95rem;
    font-style: italic;
    color: var(--color-ivory);
    margin-bottom: 0.35rem;
  }

  .philosophy-trans {
    display: block;
    font-size: 0.78rem;
    color: rgba(246, 241, 231, 0.6);
  }

  .footer-heading {
    color: var(--color-ivory);
    font-family: var(--font-sans);
    font-size: 1rem;
    font-weight: 700;
    margin-bottom: 1.25rem;
    position: relative;
    padding-bottom: 0.5rem;
  }

  .footer-heading::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 28px;
    height: 2px;
    background-color: var(--color-terracotta);
  }

  .footer-list {
    list-style: none;
    display: flex;
    flex-direction: column;
    gap: 0.75rem;
  }

  .footer-list a {
    color: rgba(246, 241, 231, 0.72);
    font-size: 0.88rem;
    transition: color var(--transition-fast);
  }

  .footer-list a:hover {
    color: var(--color-lake);
  }

  .initiative-badge {
    background: rgba(201, 110, 72, 0.12);
    border: 1px solid rgba(201, 110, 72, 0.3);
    border-radius: var(--radius-md);
    padding: 1rem;
    margin-bottom: 1.25rem;
  }

  .badge-tag {
    display: inline-block;
    font-size: 0.68rem;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 0.06em;
    color: var(--color-terracotta);
    margin-bottom: 0.35rem;
  }

  .initiative-title {
    font-weight: 600;
    font-size: 0.88rem;
    color: var(--color-ivory);
    margin-bottom: 0.35rem;
  }

  .initiative-sub {
    font-size: 0.78rem;
    color: rgba(246, 241, 231, 0.65);
    line-height: 1.4;
  }

  .contact-label {
    display: block;
    font-size: 0.78rem;
    color: rgba(246, 241, 231, 0.7);
    margin-bottom: 0.35rem;
  }

  .footer-wa-link {
    display: inline-flex;
    align-items: center;
    gap: 0.4rem;
    font-size: 0.86rem;
    font-weight: 600;
    color: #4ADE80;
    transition: transform var(--transition-fast);
  }

  .footer-wa-link:hover {
    transform: translateX(3px);
  }

  .footer-bottom {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding-top: 2rem;
    border-top: 1px solid rgba(255, 255, 255, 0.08);
    font-size: 0.82rem;
    color: rgba(246, 241, 231, 0.5);
  }

  .footer-bottom-links {
    display: flex;
    align-items: center;
    gap: 0.75rem;
  }

  .footer-bottom-links a {
    color: rgba(246, 241, 231, 0.65);
  }

  .footer-bottom-links a:hover {
    color: var(--color-ivory);
  }

  @media (max-width: 992px) {
    .footer-top {
      grid-template-columns: 1fr 1fr;
      gap: 2.5rem;
    }
  }

  @media (max-width: 640px) {
    .footer-top {
      grid-template-columns: 1fr;
    }
    .footer-bottom {
      flex-direction: column;
      gap: 1rem;
      text-align: center;
    }
  }
</style>
