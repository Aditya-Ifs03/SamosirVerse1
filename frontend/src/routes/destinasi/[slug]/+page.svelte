<script lang="ts">
  import { onMount } from 'svelte';
  import { page } from '$app/stores';
  import { api } from '$lib/api';
  import type { Destination } from '$lib/types';
  import InteractiveMap from '$lib/components/InteractiveMap.svelte';

  let destination: Destination | null = null;
  let loading = true;
  let errorMsg = '';

  $: slug = $page.params.slug ?? '';

  onMount(async () => {
    try {
      const currentSlug = $page.params.slug || '';
      destination = await api.getDestinationBySlug(currentSlug);
    } catch (err: any) {
      errorMsg = err.message || 'Gagal memuat detail destinasi';
    } finally {
      loading = false;
    }
  });

  function formatPrice(val: number): string {
    if (val === 0) return 'Gratis / Sukarela';
    return `Rp ${val.toLocaleString('id-ID')}`;
  }

  function formatCategory(cat: string): string {
    switch (cat) {
      case 'BUDAYA_SEJARAH': return 'Budaya & Sejarah Batak';
      case 'ALAM': return 'Panorama Alam Kaldera';
      case 'KULINER_UMKM': return 'Kriya & Tenun Ulos';
      case 'PETUALANGAN_RELIGI': return 'Petualangan & Situs Sakral';
      default: return cat;
    }
  }
</script>

<svelte:head>
  <title>{destination ? `${destination.name} — SamosirVerse` : 'Detail Destinasi — SamosirVerse'}</title>
</svelte:head>

<div class="destination-detail-page">
  {#if loading}
    <div class="state-container">
      <div class="spinner"></div>
      <p>Memuat informasi destinasi...</p>
    </div>
  {:else if errorMsg || !destination}
    <div class="state-container">
      <h2>Destinasi Tidak Ditemukan</h2>
      <p>{errorMsg || 'Maaf, destinasi yang Anda tuju tidak tersedia.'}</p>
      <a href="/destinasi" class="btn btn-primary btn-sm">&larr; Kembali ke Katalog Destinasi</a>
    </div>
  {:else}
    <!-- Detail Hero -->
    <header class="detail-hero" style="background-image: linear-gradient(180deg, rgba(18, 60, 54, 0.4) 0%, rgba(18, 60, 54, 0.95) 100%), url('{destination.imageUrl}');">
      <div class="container hero-inner">
        <a href="/destinasi" class="back-link">
          <span>&larr; Kembali ke Katalog</span>
        </a>

        <div class="hero-meta-badges">
          <span class="badge badge-lake">{formatCategory(destination.category)}</span>
          <span class="badge badge-forest" style="background: rgba(255,255,255,0.2); color: #fff;">📍 {destination.subDistrict}, Samosir</span>
          <span class="badge badge-terracotta" style="background: rgba(201,110,72,0.9); color: #fff;">★ {destination.rating.toFixed(1)} ({destination.reviewCount} ulasan)</span>
        </div>

        <h1 class="hero-name">{destination.name}</h1>
        <p class="hero-short-desc">{destination.shortDescription}</p>
      </div>
    </header>

    <!-- Main Content Container -->
    <div class="container content-container">
      <div class="detail-grid">
        <!-- Left Column: Story, Significance, Facilities -->
        <div class="main-column">
          <!-- Overview -->
          <section class="section-card card-editorial">
            <h2 class="section-heading">Tentang Destinasi</h2>
            <div class="rich-text">
              <p>{destination.description}</p>
            </div>
          </section>

          <!-- Cultural Significance -->
          {#if destination.culturalSignificance}
            <section class="section-card card-editorial bg-significance">
              <div class="significance-header">
                <span class="significance-icon">🏛️</span>
                <h3 class="significance-title">Nilai & Falsafah Budaya Batak Toba</h3>
              </div>
              <p class="significance-text">
                {destination.culturalSignificance}
              </p>
            </section>
          {/if}

          <!-- Facilities -->
          {#if destination.facilities}
            <section class="section-card card-editorial">
              <h2 class="section-heading">Fasilitas Tersedia</h2>
              <div class="facilities-tags">
                {#each destination.facilities.split(',') as fac}
                  <div class="facility-pill">
                    <span class="fac-check">✓</span>
                    <span>{fac.trim()}</span>
                  </div>
                {/each}
              </div>
            </section>
          {/if}

          <!-- Interactive Map Location -->
          <section class="section-card card-editorial">
            <h2 class="section-heading">Lokasi Geospasial Presisi</h2>
            <p class="map-helper">
              Koordinat: {destination.latitude.toFixed(4)}, {destination.longitude.toFixed(4)} — Pulau Samosir
            </p>
            <div class="detail-map-box">
              <InteractiveMap destinations={[destination]} height="360px" />
            </div>
          </section>
        </div>

        <!-- Right Column: Sidebar Action & Practical Info -->
        <aside class="sidebar-column">
          <div class="info-card card-editorial">
            <h3 class="info-card-title">Informasi Kunjungan</h3>

            <div class="info-item">
              <span class="info-label">Tiket Masuk</span>
              <span class="info-value price-highlight">{formatPrice(destination.ticketPrice)}</span>
            </div>

            <div class="info-item">
              <span class="info-label">Jam Operasional</span>
              <span class="info-value">🕒 {destination.openingHours}</span>
            </div>

            <div class="info-item">
              <span class="info-label">Estimasi Durasi</span>
              <span class="info-value">⏱ {destination.estimatedDurationHours} Jam</span>
            </div>

            <div class="info-item">
              <span class="info-label">Wilayah</span>
              <span class="info-value">📍 Kecamatan {destination.subDistrict}</span>
            </div>

            <div class="sidebar-action-divider"></div>

            <a href="/trip-planner" class="btn btn-primary w-full text-center">
              <span>✨ Masukkan ke Trip Planner</span>
            </a>

            <a
              href="https://www.google.com/maps/dir/?api=1&destination={destination.latitude},{destination.longitude}"
              target="_blank"
              rel="noopener noreferrer"
              class="btn btn-secondary w-full text-center"
              style="margin-top: 0.75rem;"
            >
              <span>🧭 Buka Petunjuk Arah Google Maps</span>
            </a>
          </div>

          <!-- Cultural Reminder Card -->
          <div class="etiquette-reminder card-editorial">
            <span class="etiquette-tag">Tata Krama Kunjungan</span>
            <p class="etiquette-text">
              Di situs budaya ini, hormati tatanan adat setempat. Ucapkan salam <em>"Horas"</em> dan mintalah izin kepada tetua atau penjaga huta sebelum memotret benda pusaka.
            </p>
          </div>
        </aside>
      </div>
    </div>
  {/if}
</div>

<style>
  .destination-detail-page {
    padding-bottom: 5rem;
  }

  .detail-hero {
    position: relative;
    padding: 6rem 0 4rem;
    background-size: cover;
    background-position: center;
    color: var(--color-ivory);
  }

  .hero-inner {
    max-width: 900px;
  }

  .back-link {
    display: inline-block;
    color: rgba(246, 241, 231, 0.8);
    font-weight: 600;
    font-size: 0.9rem;
    margin-bottom: 1.5rem;
    transition: color var(--transition-fast);
  }

  .back-link:hover {
    color: var(--color-ivory);
  }

  .hero-meta-badges {
    display: flex;
    flex-wrap: wrap;
    gap: 0.6rem;
    margin-bottom: 1rem;
  }

  .hero-name {
    font-size: 3rem;
    color: var(--color-ivory);
    margin-bottom: 1rem;
    line-height: 1.2;
  }

  .hero-short-desc {
    font-size: 1.15rem;
    line-height: 1.6;
    color: rgba(246, 241, 231, 0.9);
  }

  .content-container {
    margin-top: 2.5rem;
  }

  .detail-grid {
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 2.5rem;
  }

  .main-column {
    display: flex;
    flex-direction: column;
    gap: 2rem;
  }

  .section-card {
    padding: 2rem;
  }

  .section-heading {
    font-size: 1.5rem;
    margin-bottom: 1.25rem;
    color: var(--color-forest);
  }

  .rich-text p {
    font-size: 1rem;
    line-height: 1.8;
    color: var(--color-charcoal);
  }

  .bg-significance {
    background: linear-gradient(135deg, rgba(201, 110, 72, 0.08) 0%, rgba(246, 241, 231, 0.6) 100%);
    border: 1px solid rgba(201, 110, 72, 0.25);
  }

  .significance-header {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    margin-bottom: 0.75rem;
  }

  .significance-icon {
    font-size: 1.5rem;
  }

  .significance-title {
    font-size: 1.2rem;
    color: var(--color-terracotta);
  }

  .significance-text {
    font-size: 0.95rem;
    color: var(--color-charcoal);
    line-height: 1.7;
    font-style: italic;
  }

  .facilities-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 0.75rem;
  }

  .facility-pill {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    background: var(--color-ivory);
    border: 1px solid var(--color-border);
    padding: 0.5rem 1rem;
    border-radius: var(--radius-full);
    font-size: 0.88rem;
    color: var(--color-forest);
    font-weight: 500;
  }

  .fac-check {
    color: var(--color-forest);
    font-weight: 800;
  }

  .map-helper {
    font-size: 0.85rem;
    color: var(--color-muted);
    margin-bottom: 1rem;
  }

  .sidebar-column {
    display: flex;
    flex-direction: column;
    gap: 1.75rem;
  }

  .info-card {
    padding: 1.75rem;
    position: sticky;
    top: 90px;
  }

  .info-card-title {
    font-size: 1.25rem;
    margin-bottom: 1.25rem;
    padding-bottom: 0.75rem;
    border-bottom: 1px solid var(--color-border);
  }

  .info-item {
    display: flex;
    flex-direction: column;
    margin-bottom: 1rem;
  }

  .info-label {
    font-size: 0.75rem;
    text-transform: uppercase;
    color: var(--color-muted);
    font-weight: 700;
    margin-bottom: 0.2rem;
  }

  .info-value {
    font-size: 0.95rem;
    font-weight: 600;
    color: var(--color-charcoal);
  }

  .price-highlight {
    font-size: 1.35rem;
    color: var(--color-terracotta);
    font-weight: 700;
  }

  .sidebar-action-divider {
    height: 1px;
    background-color: var(--color-border);
    margin: 1.25rem 0;
  }

  .w-full {
    width: 100%;
  }

  .text-center {
    text-align: center;
  }

  .etiquette-reminder {
    padding: 1.25rem;
    background: var(--color-ivory);
    border: 1px dashed var(--color-border);
  }

  .etiquette-tag {
    display: inline-block;
    font-size: 0.7rem;
    font-weight: 700;
    color: var(--color-forest);
    text-transform: uppercase;
    letter-spacing: 0.06em;
    margin-bottom: 0.4rem;
  }

  .etiquette-text {
    font-size: 0.85rem;
    color: var(--color-muted);
    line-height: 1.5;
  }

  .state-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 6rem 1rem;
    text-align: center;
    gap: 1rem;
  }

  .spinner {
    width: 40px;
    height: 40px;
    border: 3px solid var(--color-border);
    border-top-color: var(--color-forest);
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
  }

  @keyframes spin {
    to { transform: rotate(360deg); }
  }

  @media (max-width: 992px) {
    .detail-grid {
      grid-template-columns: 1fr;
    }
    .info-card {
      position: static;
    }
  }

  @media (max-width: 640px) {
    .hero-name {
      font-size: 2.2rem;
    }
  }
</style>
