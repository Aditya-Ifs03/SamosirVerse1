<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { Destination } from '$lib/types';
  import InteractiveMap from '$lib/components/InteractiveMap.svelte';

  let destinations: Destination[] = [];
  let loading = true;
  let selectedCategory = '';

  const categories = [
    { value: '', label: 'Semua Titik' },
    { value: 'BUDAYA_SEJARAH', label: '🏛️ Budaya & Sejarah' },
    { value: 'ALAM', label: '🌄 Alam Kaldera' },
    { value: 'KULINER_UMKM', label: '🧶 Tenun & Kriya' },
    { value: 'PETUALANGAN_RELIGI', label: '⛰️ Petualangan & Sakral' }
  ];

  onMount(async () => {
    try {
      destinations = await api.getDestinations();
    } catch (e) {
      console.error(e);
    } finally {
      loading = false;
    }
  });

  $: filtered = selectedCategory
    ? destinations.filter(d => d.category === selectedCategory)
    : destinations;
</script>

<svelte:head>
  <title>Peta Interaktif Geopark Samosir — SamosirVerse</title>
</svelte:head>

<div class="map-page">
  <div class="container">
    <header class="map-header text-center">
      <span class="badge badge-lake">Sistem Informasi Geospasial</span>
      <h1 class="page-title">Peta Interaktif Geopark & Budaya Pulau Samosir</h1>
      <p class="page-desc">
        Jelajahi sebaran titik koordinat presisi destinasi sejarah purba, situs alam kaldera Danau Toba, hingga sanggar penenun ulos di sekeliling Pulau Samosir.
      </p>

      <!-- Category Filter Pills -->
      <div class="category-pills">
        {#each categories as cat}
          <button
            class="pill-btn {selectedCategory === cat.value ? 'pill-active' : ''}"
            on:click={() => selectedCategory = cat.value}
          >
            {cat.label}
          </button>
        {/each}
      </div>
    </header>

    <div class="map-layout">
      <!-- Map Column -->
      <div class="map-main-box">
        {#if loading}
          <div class="map-loading">
            <div class="spinner"></div>
            <p>Memuat peta satelit dan titik koordinat Samosir...</p>
          </div>
        {:else}
          <InteractiveMap destinations={filtered} {selectedCategory} height="650px" />
        {/if}
      </div>

      <!-- Sidebar Location List -->
      <aside class="locations-sidebar card-editorial">
        <h3 class="sidebar-title">
          <span>Daftar Titik ({filtered.length})</span>
        </h3>

        <div class="sidebar-scroll">
          {#each filtered as dest}
            <a href="/destinasi/{dest.slug}" class="location-item">
              <img src={dest.imageUrl} alt={dest.name} class="loc-thumb" />
              <div class="loc-info">
                <span class="loc-sub">{dest.subDistrict}</span>
                <h4 class="loc-name">{dest.name}</h4>
                <div class="loc-price">
                  {dest.ticketPrice === 0 ? 'Gratis' : 'Rp ' + dest.ticketPrice.toLocaleString('id-ID')}
                </div>
              </div>
            </a>
          {/each}
        </div>
      </aside>
    </div>
  </div>
</div>

<style>
  .map-page {
    padding: 3rem 0 5rem;
  }

  .text-center {
    text-align: center;
    max-width: 820px;
    margin: 0 auto 2.5rem;
  }

  .page-title {
    font-size: 2.5rem;
    margin: 0.75rem 0;
  }

  .page-desc {
    font-size: 1.05rem;
    color: var(--color-muted);
    line-height: 1.6;
    margin-bottom: 1.75rem;
  }

  .category-pills {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 0.6rem;
  }

  .pill-btn {
    padding: 0.45rem 1.1rem;
    border-radius: var(--radius-full);
    border: 1.5px solid var(--color-border);
    background-color: var(--color-white);
    font-size: 0.86rem;
    font-weight: 600;
    color: var(--color-forest);
    transition: all var(--transition-fast);
  }

  .pill-btn:hover {
    border-color: var(--color-forest);
    background-color: var(--color-ivory);
  }

  .pill-active {
    background-color: var(--color-forest) !important;
    color: var(--color-white) !important;
    border-color: var(--color-forest) !important;
    box-shadow: 0 4px 12px rgba(18, 60, 54, 0.2);
  }

  .map-layout {
    display: grid;
    grid-template-columns: 1fr 340px;
    gap: 2rem;
  }

  .map-main-box {
    width: 100%;
  }

  .map-loading {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 650px;
    background: var(--color-white);
    border-radius: var(--radius-lg);
    border: 1px solid var(--color-border);
    gap: 1rem;
    color: var(--color-muted);
  }

  .spinner {
    width: 42px;
    height: 42px;
    border: 3px solid var(--color-border);
    border-top-color: var(--color-forest);
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
  }

  @keyframes spin {
    to { transform: rotate(360deg); }
  }

  .locations-sidebar {
    display: flex;
    flex-direction: column;
    height: 650px;
    padding: 1.25rem;
  }

  .sidebar-title {
    font-size: 1.15rem;
    padding-bottom: 0.75rem;
    margin-bottom: 0.75rem;
    border-bottom: 1px solid var(--color-border);
  }

  .sidebar-scroll {
    overflow-y: auto;
    display: flex;
    flex-direction: column;
    gap: 0.75rem;
    padding-right: 0.25rem;
  }

  .location-item {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    padding: 0.6rem;
    border-radius: var(--radius-sm);
    background-color: var(--color-ivory-light);
    border: 1px solid transparent;
    transition: all var(--transition-fast);
  }

  .location-item:hover {
    border-color: var(--color-lake);
    background-color: var(--color-white);
    transform: translateX(3px);
  }

  .loc-thumb {
    width: 60px;
    height: 60px;
    border-radius: var(--radius-sm);
    object-fit: cover;
    flex-shrink: 0;
  }

  .loc-info {
    flex: 1;
    min-width: 0;
  }

  .loc-sub {
    font-size: 0.7rem;
    color: var(--color-muted);
    font-weight: 600;
  }

  .loc-name {
    font-size: 0.9rem;
    color: var(--color-forest);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 1.25;
    margin: 0.15rem 0;
  }

  .loc-price {
    font-size: 0.8rem;
    font-weight: 700;
    color: var(--color-terracotta);
  }

  @media (max-width: 992px) {
    .map-layout {
      grid-template-columns: 1fr;
    }
    .locations-sidebar {
      height: 400px;
    }
  }
</style>
