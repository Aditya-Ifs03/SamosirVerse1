<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { CulturalStory } from '$lib/types';
  import StoryCard from '$lib/components/StoryCard.svelte';

  let stories: CulturalStory[] = [];
  let loading = true;
  let selectedCategory = '';
  let searchKeyword = '';

  const categories = [
    { value: '', label: 'Seluruh Kategori' },
    { value: 'FILOSOFI', label: '📖 Falsafah Hidup' },
    { value: 'LEGENDA', label: '✨ Legenda & Folklor' },
    { value: 'TRADISI', label: '🧶 Tradisi & Wastra Ulos' },
    { value: 'SITUS_SEJARAH', label: '🏛️ Situs Sejarah' }
  ];

  async function loadStories() {
    loading = true;
    try {
      stories = await api.getCulturalStories(selectedCategory, searchKeyword);
    } catch (err) {
      console.error(err);
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    loadStories();
  });
</script>

<svelte:head>
  <title>Warisan Cerita Budaya Batak Toba — SamosirVerse</title>
</svelte:head>

<div class="budaya-page">
  <header class="page-header text-center">
    <div class="container">
      <span class="badge badge-terracotta">Warisan Takbenda Batak Toba</span>
      <h1 class="header-title">Cerita & Falsafah Budaya Leluhur</h1>
      <p class="header-desc">
        Menyelami kedalaman filosofi Dalihan Na Tolu, makna sakral tenun Ulos, seni boneka Sigale-gale, dan narasi peradaban Batak Toba di tanah Samosir.
      </p>
    </div>
  </header>

  <div class="container">
    <!-- Filter bar -->
    <div class="filter-bar card-editorial">
      <div class="filter-bar-grid">
        <input
          type="text"
          class="form-input"
          placeholder="Cari cerita budaya, legenda, atau falsafah..."
          bind:value={searchKeyword}
          on:input={loadStories}
        />
        <div class="cat-buttons">
          {#each categories as cat}
            <button
              class="cat-btn {selectedCategory === cat.value ? 'cat-active' : ''}"
              on:click={() => { selectedCategory = cat.value; loadStories(); }}
            >
              {cat.label}
            </button>
          {/each}
        </div>
      </div>
    </div>

    {#if loading}
      <div class="state-container">
        <div class="spinner"></div>
        <p>Menyiapkan artikel warisan budaya...</p>
      </div>
    {:else if stories.length === 0}
      <div class="state-container">
        <p>Tidak ada artikel budaya yang cocok dengan pencarian Anda.</p>
        <button on:click={() => { selectedCategory = ''; searchKeyword = ''; loadStories(); }} class="btn btn-secondary btn-sm">Reset</button>
      </div>
    {:else}
      <div class="stories-grid">
        {#each stories as story (story.id)}
          <StoryCard {story} />
        {/each}
      </div>
    {/if}
  </div>
</div>

<style>
  .budaya-page {
    padding-bottom: 5rem;
  }

  .page-header {
    background: linear-gradient(180deg, rgba(201, 110, 72, 0.08) 0%, transparent 100%);
    padding: 3.5rem 0 2rem;
    margin-bottom: 2rem;
  }

  .text-center {
    text-align: center;
    max-width: 800px;
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

  .filter-bar {
    padding: 1.5rem;
    margin-bottom: 2.5rem;
  }

  .filter-bar-grid {
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }

  .cat-buttons {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
  }

  .cat-btn {
    padding: 0.45rem 1rem;
    border-radius: var(--radius-full);
    border: 1.5px solid var(--color-border);
    background-color: var(--color-white);
    font-size: 0.85rem;
    font-weight: 600;
    color: var(--color-forest);
    transition: all var(--transition-fast);
  }

  .cat-btn:hover {
    border-color: var(--color-forest);
  }

  .cat-active {
    background-color: var(--color-terracotta) !important;
    color: var(--color-white) !important;
    border-color: var(--color-terracotta) !important;
  }

  .stories-grid {
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
    border-top-color: var(--color-terracotta);
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
  }

  @keyframes spin {
    to { transform: rotate(360deg); }
  }

  @media (max-width: 992px) {
    .stories-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 640px) {
    .stories-grid {
      grid-template-columns: 1fr;
    }
    .header-title {
      font-size: 2rem;
    }
  }
</style>
