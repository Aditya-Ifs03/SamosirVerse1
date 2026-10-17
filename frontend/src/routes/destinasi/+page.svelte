<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { Destination } from '$lib/types';
  import DestinationCard from '$lib/components/DestinationCard.svelte';

  let destinations: Destination[] = [];
  let loading = true;
  let searchKeyword = '';
  let selectedCategory = '';
  let selectedSubDistrict = '';

  const categories = [
    { value: '', label: 'Semua Kategori' },
    { value: 'BUDAYA_SEJARAH', label: '🏛️ Budaya & Sejarah' },
    { value: 'ALAM', label: '🌄 Panorama Alam' },
    { value: 'KULINER_UMKM', label: '🧶 Kriya & Tenun' },
    { value: 'PETUALANGAN_RELIGI', label: '⛰️ Petualangan & Religi' }
  ];

  const subDistricts = [
    { value: '', label: 'Seluruh Samosir' },
    { value: 'Simanindo', label: 'Kec. Simanindo' },
    { value: 'Pangururan', label: 'Kec. Pangururan' },
    { value: 'Harian', label: 'Kec. Harian' },
    { value: 'Sitio-tio', label: 'Kec. Sitio-tio' },
    { value: 'Onan Runggu', label: 'Kec. Onan Runggu' },
    { value: 'Palipi', label: 'Kec. Palipi' }
  ];

  async function loadData() {
    loading = true;
    try {
      destinations = await api.getDestinations(selectedCategory, selectedSubDistrict, searchKeyword);
    } catch (e) {
      console.error(e);
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    // Check URL search parameters
    if (typeof window !== 'undefined') {
      const urlParams = new URLSearchParams(window.location.search);
      const catParam = urlParams.get('category');
      const subParam = urlParams.get('subDistrict');
      if (catParam) selectedCategory = catParam;
      if (subParam) selectedSubDistrict = subParam;
    }
    loadData();
  });

  function handleFilter() {
    loadData();
  }

  function resetFilter() {
    searchKeyword = '';
    selectedCategory = '';
    selectedSubDistrict = '';
    loadData();
  }
</script>

<svelte:head>
  <title>Katalog Destinasi Wisata — SamosirVerse</title>
</svelte:head>

<div class="destinasi-page">
  <!-- Page Header -->
  <header class="page-header">
    <div class="container text-center">
      <span class="badge badge-lake">Eksplorasi Geopark & Sejarah</span>
      <h1 class="header-title">Destinasi Wisata Pulau Samosir</h1>
      <p class="header-desc">
        Temukan pesona alam kaldera Danau Toba, kampung adat kuno berbenteng batu, air terjun tersembunyi, hingga puncak tertinggi sakral Batak Toba.
      </p>
    </div>
  </header>

  <div class="container">
    <!-- Filter & Search Bar -->
    <div class="filter-card card-editorial">
      <div class="filter-grid">
        <div class="search-input-wrapper">
          <label class="form-label" for="search-input">Cari Nama Destinasi atau Kata Kunci</label>
          <input
            id="search-input"
            type="text"
            class="form-input"
            placeholder="Contoh: Huta Siallagan, Tomok, Holbung, Air Terjun..."
            bind:value={searchKeyword}
            on:input={handleFilter}
          />
        </div>

        <div class="filter-select-wrapper">
          <label class="form-label" for="category-select">Kategori Wisata</label>
          <select id="category-select" class="form-select" bind:value={selectedCategory} on:change={handleFilter}>
            {#each categories as cat}
              <option value={cat.value}>{cat.label}</option>
            {/each}
          </select>
        </div>

        <div class="filter-select-wrapper">
          <label class="form-label" for="subdistrict-select">Kecamatan</label>
          <select id="subdistrict-select" class="form-select" bind:value={selectedSubDistrict} on:change={handleFilter}>
            {#each subDistricts as sub}
              <option value={sub.value}>{sub.label}</option>
            {/each}
          </select>
        </div>

        <div class="filter-action-wrapper">
          <button on:click={resetFilter} class="btn btn-secondary btn-sm w-full">
            Reset Filter
          </button>
        </div>
      </div>
    </div>

    <!-- Results Section -->
    {#if loading}
      <div class="state-container">
        <div class="spinner"></div>
        <p>Menyiapkan daftar destinasi...</p>
      </div>
    {:else if destinations.length === 0}
      <div class="state-container empty-state">
        <span class="empty-icon">🍃</span>
        <h3>Tidak Ditemukan Destinasi</h3>
        <p>Tidak ada destinasi yang cocok dengan kata kunci atau filter yang Anda pilih.</p>
        <button on:click={resetFilter} class="btn btn-primary btn-sm">Tampilkan Semua Destinasi</button>
      </div>
    {:else}
      <div class="results-header">
        <span>Menampilkan <strong>{destinations.length}</strong> destinasi di Pulau Samosir</span>
      </div>

      <div class="destinations-grid">
        {#each destinations as dest (dest.id)}
          <DestinationCard destination={dest} />
        {/each}
      </div>
    {/if}
  </div>
</div>

<style>
  .destinasi-page {
    padding-bottom: 5rem;
  }

  .page-header {
    background: linear-gradient(180deg, rgba(18, 60, 54, 0.08) 0%, transparent 100%);
    padding: 3.5rem 0 2.5rem;
    margin-bottom: 2rem;
  }

  .text-center {
    text-align: center;
    max-width: 780px;
    margin: 0 auto;
  }

  .header-title {
    font-size: 2.75rem;
    margin: 0.75rem 0;
  }

  .header-desc {
    font-size: 1.05rem;
    color: var(--color-muted);
    line-height: 1.6;
  }

  .filter-card {
    padding: 1.75rem;
    margin-bottom: 2.5rem;
    background-color: var(--color-white);
  }

  .filter-grid {
    display: grid;
    grid-template-columns: 2fr 1.3fr 1.3fr 1fr;
    gap: 1.25rem;
    align-items: flex-end;
  }

  .w-full {
    width: 100%;
  }

  .results-header {
    font-size: 0.92rem;
    color: var(--color-muted);
    margin-bottom: 1.5rem;
  }

  .results-header strong {
    color: var(--color-forest);
  }

  .destinations-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 2rem;
  }

  .state-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 5rem 1rem;
    text-align: center;
    color: var(--color-muted);
    gap: 1rem;
  }

  .empty-icon {
    font-size: 3rem;
  }

  .empty-state h3 {
    font-size: 1.5rem;
    color: var(--color-forest);
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
    .filter-grid {
      grid-template-columns: 1fr 1fr;
    }
    .destinations-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 640px) {
    .filter-grid {
      grid-template-columns: 1fr;
    }
    .destinations-grid {
      grid-template-columns: 1fr;
    }
    .header-title {
      font-size: 2rem;
    }
  }
</style>
