<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { Experience } from '$lib/types';
  import ExperienceCard from '$lib/components/ExperienceCard.svelte';

  let experiences: Experience[] = [];
  let loading = true;
  let selectedType = '';
  let searchKeyword = '';

  const types = [
    { value: '', label: 'Semua Agenda' },
    { value: 'WORKSHOP', label: '🧶 Workshop Kriya' },
    { value: 'TUR_BUDAYA', label: '🧭 Tur Jelajah Budaya' },
    { value: 'FESTIVAL_AGENDA', label: '🎶 Festival & Seni' },
    { value: 'KULINER', label: '🍳 Kuliner Batak' }
  ];

  async function loadExperiences() {
    loading = true;
    try {
      experiences = await api.getExperiences(selectedType, searchKeyword);
    } catch (err) {
      console.error(err);
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    loadExperiences();
  });
</script>

<svelte:head>
  <title>Agenda & Pengalaman Wisata Samosir — SamosirVerse</title>
</svelte:head>

<div class="agenda-page">
  <header class="page-header text-center">
    <div class="container">
      <span class="badge badge-lake">Agenda Budaya & Partisipasi Aktif</span>
      <h1 class="header-title">Pengalaman Lokal & Festival Danau Toba</h1>
      <p class="header-desc">
        Bergabunglah dalam workshop interaktif menenun ulos pewarna alam, trekking spiritual fajar Pusuk Buhit, dan kemeriahan panggung musik internasional berlatar kaldera Danau Toba.
      </p>
    </div>
  </header>

  <div class="container">
    <div class="filter-card card-editorial">
      <div class="filter-grid">
        <input
          type="text"
          class="form-input"
          placeholder="Cari kegiatan, workshop, atau festival..."
          bind:value={searchKeyword}
          on:input={loadExperiences}
        />
        <div class="type-buttons">
          {#each types as t}
            <button
              class="type-btn {selectedType === t.value ? 'type-active' : ''}"
              on:click={() => { selectedType = t.value; loadExperiences(); }}
            >
              {t.label}
            </button>
          {/each}
        </div>
      </div>
    </div>

    {#if loading}
      <div class="state-container">
        <div class="spinner"></div>
        <p>Menyiapkan kalender pengalaman lokal...</p>
      </div>
    {:else if experiences.length === 0}
      <div class="state-container">
        <p>Tidak ada agenda kegiatan yang cocok dengan kriteria pencarian Anda.</p>
        <button on:click={() => { selectedType = ''; searchKeyword = ''; loadExperiences(); }} class="btn btn-secondary btn-sm">Reset</button>
      </div>
    {:else}
      <div class="experiences-grid">
        {#each experiences as exp (exp.id)}
          <ExperienceCard experience={exp} />
        {/each}
      </div>
    {/if}
  </div>
</div>

<style>
  .agenda-page {
    padding-bottom: 5rem;
  }

  .page-header {
    background: linear-gradient(180deg, rgba(138, 185, 194, 0.12) 0%, transparent 100%);
    padding: 3.5rem 0 2rem;
    margin-bottom: 2rem;
  }

  .text-center {
    text-align: center;
    max-width: 820px;
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
    padding: 1.5rem;
    margin-bottom: 2.5rem;
  }

  .filter-grid {
    display: flex;
    flex-direction: column;
    gap: 1.25rem;
  }

  .type-buttons {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
  }

  .type-btn {
    padding: 0.45rem 1rem;
    border-radius: var(--radius-full);
    border: 1.5px solid var(--color-border);
    background-color: var(--color-white);
    font-size: 0.85rem;
    font-weight: 600;
    color: var(--color-forest);
    transition: all var(--transition-fast);
  }

  .type-btn:hover {
    border-color: var(--color-forest);
  }

  .type-active {
    background-color: var(--color-forest) !important;
    color: var(--color-white) !important;
    border-color: var(--color-forest) !important;
  }

  .experiences-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 2rem;
  }

  .state-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 5rem 1rem;
    gap: 1rem;
    color: var(--color-muted);
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
    .experiences-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 640px) {
    .experiences-grid {
      grid-template-columns: 1fr;
    }
    .header-title {
      font-size: 2rem;
    }
  }
</style>
