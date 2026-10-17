<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { Destination, Creator, CulturalStory, Experience, TripPlanResponse } from '$lib/types';
  import DestinationCard from '$lib/components/DestinationCard.svelte';
  import CreatorCard from '$lib/components/CreatorCard.svelte';
  import StoryCard from '$lib/components/StoryCard.svelte';
  import ExperienceCard from '$lib/components/ExperienceCard.svelte';
  import InteractiveMap from '$lib/components/InteractiveMap.svelte';

  let destinations: Destination[] = [];
  let featuredDestinations: Destination[] = [];
  let creators: Creator[] = [];
  let stories: CulturalStory[] = [];
  let experiences: Experience[] = [];
  let loading = true;

  // Quick Trip Planner Widget State
  let planDays = 2;
  let planStyle = 'BUDAYA';
  let planBudget = 'STANDAR';
  let plannerResult: TripPlanResponse | null = null;
  let planningLoading = false;

  onMount(async () => {
    try {
      const [allDests, featDests, allCreat, allStories, allExps] = await Promise.all([
        api.getDestinations(),
        api.getFeaturedDestinations(),
        api.getCreators(),
        api.getCulturalStories(),
        api.getExperiences()
      ]);
      destinations = allDests;
      featuredDestinations = featDests;
      creators = allCreat;
      stories = allStories;
      experiences = allExps;
    } catch (e) {
      console.error(e);
    } finally {
      loading = false;
    }
  });

  async function handleQuickPlan() {
    planningLoading = true;
    try {
      plannerResult = await api.generateTripPlan({
        durationDays: planDays,
        travelStyle: planStyle,
        budgetCategory: planBudget,
        groupType: 'PASANGAN'
      });
    } catch (err) {
      console.error(err);
    } finally {
      planningLoading = false;
    }
  }
</script>

<svelte:head>
  <title>SamosirVerse — Ekosistem Budaya, Wisata & Kreator Lokal Pulau Samosir</title>
</svelte:head>

<!-- HERO SECTION -->
<section class="hero-section">
  <div class="hero-overlay"></div>
  <div class="container hero-container">
    <div class="hero-badge badge badge-lake">
      <span>🌟 Creative Hub for App Talent Samosir 2026</span>
    </div>

    <h1 class="hero-headline">
      Jelajahi Jiwa & Lanskap Magis <span class="highlight-text">Pulau Samosir</span>
    </h1>

    <p class="hero-subtext">
      Platform ekosistem pariwisata cerdas yang mempertemukan keagungan kaldera Danau Toba, kearifan falsafah Batak Toba, dan karya otentik para maestro tenun ulos serta pengrajin lokal Samosir.
    </p>

    <!-- Hero CTA Buttons -->
    <div class="hero-cta-group">
      <a href="/trip-planner" class="btn btn-primary">
        <span>✨ Susun Itinerary Cerdas</span>
      </a>
      <a href="/destinasi" class="btn btn-secondary hero-btn-sec">
        <span>🏛️ Telusuri Destinasi</span>
      </a>
    </div>

    <!-- Quick Stats Bar -->
    <div class="hero-stats-grid">
      <div class="stat-item">
        <span class="stat-number">8+</span>
        <span class="stat-desc">Situs Wisata & Geopark Otentik</span>
      </div>
      <div class="stat-item">
        <span class="stat-number">100%</span>
        <span class="stat-desc">Kontak WhatsApp Kreator Tervalidasi</span>
      </div>
      <div class="stat-item">
        <span class="stat-number">3 Hari</span>
        <span class="stat-desc">Rencana Perjalanan Berbasis Aturan Geografis</span>
      </div>
      <div class="stat-item">
        <span class="stat-number">Falsafah</span>
        <span class="stat-desc">Dalihan Na Tolu Berakar Kuat</span>
      </div>
    </div>
  </div>
</section>

<!-- QUICK SMART TRIP PLANNER WIDGET -->
<section class="planner-widget-section">
  <div class="container">
    <div class="planner-widget-card card-editorial">
      <div class="planner-widget-header">
        <div class="badge badge-terracotta">Fitur Unggulan Cerdas</div>
        <h2 class="widget-title">Simulasi Cepat Itinerary Cerdas Samosir</h2>
        <p class="widget-subtitle">
          Pilih preferensi liburan Anda untuk mendapatkan susunan rute harian otomatis berdasarkan kedekatan geografis di Pulau Samosir.
        </p>
      </div>

      <div class="planner-controls-grid">
        <div class="control-box">
          <label class="control-label" for="duration-select">Durasi Perjalanan</label>
          <select id="duration-select" bind:value={planDays} class="form-select">
            <option value={1}>1 Hari (Eksplorasi Kilat)</option>
            <option value={2}>2 Hari 1 Malam (Rekomendasi)</option>
            <option value={3}>3 Hari 2 Malam (Penuh Mengelilingi)</option>
          </select>
        </div>

        <div class="control-box">
          <label class="control-label" for="style-select">Gaya Perjalanan</label>
          <select id="style-select" bind:value={planStyle} class="form-select">
            <option value="BUDAYA">🏛️ Budaya & Falsafah Sejarah</option>
            <option value="ALAM">🌄 Panorama Kaldera & Savana</option>
            <option value="SANTAI">☕ Santai Pesisir & Kuliner</option>
            <option value="KULINER_KREATIF">🧶 Kriya Tenun & Kopi Pusuk Buhit</option>
          </select>
        </div>

        <div class="control-box">
          <label class="control-label" for="budget-select">Kategori Anggaran</label>
          <select id="budget-select" bind:value={planBudget} class="form-select">
            <option value="HEMAT">Backpacker / Ekonomis</option>
            <option value="STANDAR">Standar Wisatawan Nyaman</option>
            <option value="PREMIUM">Resort & Private Transport</option>
          </select>
        </div>

        <div class="control-box btn-box">
          <button on:click={handleQuickPlan} class="btn btn-primary w-full" disabled={planningLoading}>
            {#if planningLoading}
              <span>Menyusun Rute...</span>
            {:else}
              <span>⚡ Generate Rute Sekarang</span>
            {/if}
          </button>
        </div>
      </div>

      {#if plannerResult}
        <div class="planner-result-box animate-fade-in">
          <div class="result-header">
            <div>
              <h3 class="result-title">{plannerResult.planTitle}</h3>
              <p class="result-summary">{plannerResult.summary}</p>
            </div>
            <div class="cost-badge">
              <span class="cost-lbl">Estimasi Total Biaya</span>
              <span class="cost-val">Rp {plannerResult.totalEstimatedCost.toLocaleString('id-ID')}</span>
            </div>
          </div>

          <div class="days-preview-grid">
            {#each plannerResult.days as day}
              <div class="day-preview-card">
                <div class="day-header">
                  <span class="day-num">HARI {day.dayNumber}</span>
                  <span class="day-theme">{day.theme}</span>
                </div>
                <ul class="activities-compact-list">
                  {#each day.activities as act}
                    <li class="act-item">
                      <span class="act-time">{act.timeSlot}</span>
                      <span class="act-name">{act.title}</span>
                    </li>
                  {/each}
                </ul>
              </div>
            {/each}
          </div>

          <div class="result-actions">
            <a href="/trip-planner" class="btn btn-forest btn-sm">
              Lihat Itinerary Lengkap & Tips Budaya &rarr;
            </a>
          </div>
        </div>
      {/if}
    </div>
  </div>
</section>

<!-- FEATURED DESTINATIONS SECTION -->
<section class="section-padding">
  <div class="container">
    <div class="section-header">
      <div class="header-left">
        <span class="section-eyebrow badge badge-forest">Destinasi Pilihan</span>
        <h2 class="section-title">Pesona Ikonik Tanah Samosir</h2>
        <p class="section-subtitle">
          Dari benteng batu pengadilan purba hingga sabana hijau menghadap kaldera terluas di dunia.
        </p>
      </div>
      <a href="/destinasi" class="view-all-link">
        <span>Lihat Seluruh Destinasi ({destinations.length}) &rarr;</span>
      </a>
    </div>

    {#if loading}
      <div class="loading-state">
        <div class="spinner"></div>
        <p>Memuat destinasi pilihan...</p>
      </div>
    {:else}
      <div class="destinations-grid">
        {#each featuredDestinations.slice(0, 3) as dest (dest.id)}
          <DestinationCard destination={dest} />
        {/each}
      </div>
    {/if}
  </div>
</section>

<!-- INTERACTIVE MAP TEASER -->
<section class="map-teaser-section">
  <div class="container">
    <div class="map-teaser-box">
      <div class="map-teaser-content">
        <span class="badge badge-lake">Peta Interaktif Geospasial</span>
        <h2 class="teaser-title">Jelajahi Sebaran Titik Wisata di Sekeliling Pulau</h2>
        <p class="teaser-text">
          Peta berbasis koordinat presisi yang memperlihatkan titik lokasi destinasi bersejarah, desa penenun ulos, dan sentra kerajinan kayu gorga di seluruh penjuru Samosir.
        </p>
        <div class="teaser-tags">
          <span class="tag-pill">📍 Simanindo & Tomok</span>
          <span class="tag-pill">📍 Pangururan & Tenun</span>
          <span class="tag-pill">📍 Harian & Menara Tele</span>
          <span class="tag-pill">📍 Bukit Holbung Sitio-tio</span>
        </div>
        <a href="/peta" class="btn btn-forest">
          <span>🗺️ Buka Peta Interaktif Lengkap</span>
        </a>
      </div>

      <div class="map-teaser-view">
        <InteractiveMap {destinations} height="420px" />
      </div>
    </div>
  </div>
</section>

<!-- BATAK TOBA CULTURAL STORIES -->
<section class="section-padding bg-ivory-light">
  <div class="container">
    <div class="section-header">
      <div class="header-left">
        <span class="section-eyebrow badge badge-terracotta">Warisan Leluhur</span>
        <h2 class="section-title">Cerita & Falsafah Budaya Batak Toba</h2>
        <p class="section-subtitle">
          Memahami nilai adiluhung Dalihan Na Tolu, makna doa di balik kain ulos, dan legenda Sigale-gale.
        </p>
      </div>
      <a href="/budaya" class="view-all-link">
        <span>Katalog Artikel Budaya &rarr;</span>
      </a>
    </div>

    <div class="stories-grid">
      {#each stories.slice(0, 3) as story (story.id)}
        <StoryCard {story} />
      {/each}
    </div>
  </div>
</section>

<!-- LOCAL CREATORS & UMKM SPOTLIGHT -->
<section class="section-padding">
  <div class="container">
    <div class="section-header">
      <div class="header-left">
        <span class="section-eyebrow badge badge-forest">Ekonomi Kreatif Lokal</span>
        <h2 class="section-title">Maestro Seni & Pengrajin Samosir</h2>
        <p class="section-subtitle">
          Dukung perajin asli dengan membeli karya langsung tanpa perantara melalui kontak WhatsApp yang telah terverifikasi resmi.
        </p>
      </div>
      <a href="/kreator" class="view-all-link">
        <span>Direktori Seluruh Kreator &rarr;</span>
      </a>
    </div>

    <div class="creators-grid">
      {#each creators.slice(0, 3) as creator (creator.id)}
        <CreatorCard {creator} />
      {/each}
    </div>
  </div>
</section>

<!-- LOCAL EXPERIENCES & FESTIVALS -->
<section class="section-padding bg-ivory-light">
  <div class="container">
    <div class="section-header">
      <div class="header-left">
        <span class="section-eyebrow badge badge-lake">Agenda & Pengalaman</span>
        <h2 class="section-title">Aktivitas Autentik & Festival Budaya</h2>
        <p class="section-subtitle">
          Ikuti kelas workshop menenun bersama maestro, trekking matahari terbit Pusuk Buhit, dan perhelatan musik internasional Danau Toba.
        </p>
      </div>
      <a href="/agenda" class="view-all-link">
        <span>Seluruh Kalender Agenda &rarr;</span>
      </a>
    </div>

    <div class="experiences-grid">
      {#each experiences.slice(0, 3) as exp (exp.id)}
        <ExperienceCard experience={exp} />
      {/each}
    </div>
  </div>
</section>

<style>
  /* Hero Styles */
  .hero-section {
    position: relative;
    padding: 7rem 0 5rem;
    background: linear-gradient(135deg, rgba(18, 60, 54, 0.95), rgba(10, 36, 32, 0.96)),
                url('https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=2000&q=80') center/cover no-repeat;
    color: var(--color-ivory);
    overflow: hidden;
  }

  .hero-container {
    position: relative;
    z-index: 2;
    max-width: 980px;
    text-align: center;
    margin: 0 auto;
  }

  .hero-badge {
    margin-bottom: 1.5rem;
    background: rgba(138, 185, 194, 0.2);
    color: var(--color-lake);
    border: 1px solid rgba(138, 185, 194, 0.4);
    font-size: 0.8rem;
    padding: 0.4rem 1rem;
  }

  .hero-headline {
    font-size: 3.5rem;
    font-weight: 700;
    line-height: 1.15;
    color: var(--color-ivory);
    margin-bottom: 1.5rem;
  }

  .highlight-text {
    color: var(--color-terracotta);
    font-style: italic;
  }

  .hero-subtext {
    font-size: 1.15rem;
    line-height: 1.7;
    color: rgba(246, 241, 231, 0.88);
    max-width: 820px;
    margin: 0 auto 2.5rem;
  }

  .hero-cta-group {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 1.25rem;
    margin-bottom: 4rem;
  }

  .hero-btn-sec {
    color: var(--color-ivory);
    border-color: rgba(246, 241, 231, 0.5);
  }

  .hero-btn-sec:hover {
    background-color: var(--color-ivory);
    color: var(--color-forest);
  }

  .hero-stats-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1.5rem;
    padding: 1.5rem;
    background: rgba(255, 255, 255, 0.08);
    backdrop-filter: blur(12px);
    border-radius: var(--radius-lg);
    border: 1px solid rgba(255, 255, 255, 0.15);
  }

  .stat-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .stat-number {
    font-family: var(--font-serif);
    font-size: 2rem;
    font-weight: 700;
    color: var(--color-terracotta);
    margin-bottom: 0.25rem;
  }

  .stat-desc {
    font-size: 0.8rem;
    color: rgba(246, 241, 231, 0.75);
    line-height: 1.4;
  }

  /* Planner Widget Section */
  .planner-widget-section {
    margin-top: -3rem;
    position: relative;
    z-index: 10;
  }

  .planner-widget-card {
    padding: 2.5rem;
    border: 2px solid var(--color-border);
  }

  .planner-widget-header {
    text-align: center;
    max-width: 700px;
    margin: 0 auto 2rem;
  }

  .widget-title {
    font-size: 1.85rem;
    margin: 0.6rem 0 0.5rem;
  }

  .widget-subtitle {
    font-size: 0.92rem;
    color: var(--color-muted);
  }

  .planner-controls-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 1.25rem;
    align-items: flex-end;
  }

  .control-label {
    display: block;
    font-size: 0.82rem;
    font-weight: 700;
    color: var(--color-forest);
    margin-bottom: 0.4rem;
  }

  .w-full {
    width: 100%;
  }

  .planner-result-box {
    margin-top: 2rem;
    padding-top: 2rem;
    border-top: 1px dashed var(--color-border);
  }

  .result-header {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    gap: 1.5rem;
    margin-bottom: 1.5rem;
  }

  .result-title {
    font-size: 1.4rem;
    color: var(--color-forest);
    margin-bottom: 0.35rem;
  }

  .result-summary {
    font-size: 0.9rem;
    color: var(--color-muted);
    max-width: 600px;
  }

  .cost-badge {
    background: var(--color-ivory);
    border: 1px solid var(--color-border);
    padding: 0.75rem 1.25rem;
    border-radius: var(--radius-md);
    display: flex;
    flex-direction: column;
    align-items: flex-end;
  }

  .cost-lbl {
    font-size: 0.72rem;
    text-transform: uppercase;
    color: var(--color-muted);
    font-weight: 700;
  }

  .cost-val {
    font-size: 1.35rem;
    font-weight: 700;
    color: var(--color-terracotta);
  }

  .days-preview-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 1.25rem;
    margin-bottom: 1.5rem;
  }

  .day-preview-card {
    background-color: var(--color-ivory-light);
    border: 1px solid var(--color-border);
    border-radius: var(--radius-md);
    padding: 1.25rem;
  }

  .day-header {
    display: flex;
    flex-direction: column;
    margin-bottom: 0.85rem;
    border-bottom: 1px solid var(--color-border);
    padding-bottom: 0.6rem;
  }

  .day-num {
    font-size: 0.72rem;
    font-weight: 800;
    color: var(--color-terracotta);
    letter-spacing: 0.08em;
  }

  .day-theme {
    font-family: var(--font-serif);
    font-size: 1rem;
    font-weight: 600;
    color: var(--color-forest);
  }

  .activities-compact-list {
    list-style: none;
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }

  .act-item {
    display: flex;
    align-items: baseline;
    gap: 0.5rem;
    font-size: 0.82rem;
  }

  .act-time {
    color: var(--color-muted);
    font-weight: 600;
    white-space: nowrap;
  }

  .act-name {
    color: var(--color-charcoal);
  }

  .result-actions {
    text-align: right;
  }

  /* Section Padding */
  .section-padding {
    padding: 5rem 0;
  }

  .bg-ivory-light {
    background-color: var(--color-ivory-light);
  }

  .section-header {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    margin-bottom: 2.5rem;
    gap: 1.5rem;
  }

  .section-eyebrow {
    margin-bottom: 0.5rem;
  }

  .section-title {
    font-size: 2.25rem;
    margin-bottom: 0.5rem;
  }

  .section-subtitle {
    font-size: 1rem;
    color: var(--color-muted);
    max-width: 600px;
  }

  .view-all-link {
    font-weight: 700;
    color: var(--color-terracotta);
    font-size: 0.95rem;
    white-space: nowrap;
    transition: transform var(--transition-fast);
  }

  .view-all-link:hover {
    transform: translateX(4px);
  }

  .destinations-grid, .stories-grid, .creators-grid, .experiences-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 2rem;
  }

  /* Map Teaser Box */
  .map-teaser-section {
    padding: 3rem 0;
  }

  .map-teaser-box {
    display: grid;
    grid-template-columns: 1fr 1.2fr;
    gap: 2.5rem;
    background-color: var(--color-white);
    border: 1px solid var(--color-border);
    border-radius: var(--radius-lg);
    padding: 2.5rem;
    box-shadow: var(--shadow-md);
    align-items: center;
  }

  .teaser-title {
    font-size: 1.85rem;
    margin: 0.75rem 0 0.85rem;
  }

  .teaser-text {
    font-size: 0.95rem;
    color: var(--color-muted);
    line-height: 1.6;
    margin-bottom: 1.5rem;
  }

  .teaser-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 0.6rem;
    margin-bottom: 2rem;
  }

  .tag-pill {
    background-color: var(--color-ivory);
    border: 1px solid var(--color-border);
    padding: 0.35rem 0.75rem;
    border-radius: var(--radius-full);
    font-size: 0.8rem;
    font-weight: 600;
    color: var(--color-forest);
  }

  .loading-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 4rem 0;
    color: var(--color-muted);
    gap: 1rem;
  }

  .spinner {
    width: 38px;
    height: 38px;
    border: 3px solid var(--color-border);
    border-top-color: var(--color-forest);
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
  }

  @keyframes spin {
    to { transform: rotate(360deg); }
  }

  @media (max-width: 1024px) {
    .hero-headline {
      font-size: 2.75rem;
    }
    .destinations-grid, .stories-grid, .creators-grid, .experiences-grid {
      grid-template-columns: repeat(2, 1fr);
    }
    .planner-controls-grid {
      grid-template-columns: repeat(2, 1fr);
    }
    .map-teaser-box {
      grid-template-columns: 1fr;
    }
  }

  @media (max-width: 768px) {
    .hero-section {
      padding: 4rem 0 3rem;
    }
    .hero-headline {
      font-size: 2.2rem;
    }
    .hero-stats-grid {
      grid-template-columns: repeat(2, 1fr);
    }
    .section-header {
      flex-direction: column;
      align-items: flex-start;
    }
    .destinations-grid, .stories-grid, .creators-grid, .experiences-grid {
      grid-template-columns: 1fr;
    }
    .planner-controls-grid {
      grid-template-columns: 1fr;
    }
  }
</style>
