<script lang="ts">
  import { onMount } from 'svelte';
  import { page } from '$app/stores';
  import { api } from '$lib/api';
  import type { CulturalStory } from '$lib/types';

  let story: CulturalStory | null = null;
  let loading = true;
  let errorMsg = '';

  $: slug = $page.params.slug ?? '';

  onMount(async () => {
    try {
      const currentSlug = $page.params.slug || '';
      story = await api.getCulturalStoryBySlug(currentSlug);
    } catch (err: any) {
      errorMsg = err.message || 'Gagal memuat artikel budaya';
    } finally {
      loading = false;
    }
  });

  function formatCategory(cat: string): string {
    switch (cat) {
      case 'FILOSOFI': return 'Falsafah Hidup';
      case 'LEGENDA': return 'Legenda & Mitos';
      case 'TRADISI': return 'Tradisi & Wastra';
      case 'SITUS_SEJARAH': return 'Situs Bersejarah';
      default: return cat;
    }
  }
</script>

<svelte:head>
  <title>{story ? `${story.title} — SamosirVerse` : 'Cerita Budaya — SamosirVerse'}</title>
</svelte:head>

<div class="story-detail-page">
  {#if loading}
    <div class="state-container">
      <div class="spinner"></div>
      <p>Memuat artikel budaya...</p>
    </div>
  {:else if errorMsg || !story}
    <div class="state-container">
      <h2>Cerita Tidak Ditemukan</h2>
      <p>{errorMsg || 'Maaf, artikel yang Anda cari tidak tersedia.'}</p>
      <a href="/budaya" class="btn btn-primary btn-sm">&larr; Kembali ke Katalog Budaya</a>
    </div>
  {:else}
    <div class="container container-narrow">
      <a href="/budaya" class="back-link">
        <span>&larr; Kembali ke Katalog Cerita Budaya</span>
      </a>

      <header class="story-header">
        <div class="story-meta">
          <span class="badge badge-terracotta">{formatCategory(story.category)}</span>
          <span class="story-reading-time">⏱ {story.readingTimeMinutes} Menit Membaca</span>
        </div>

        <h1 class="story-title">{story.title}</h1>
        <p class="story-summary-lead">{story.summary}</p>
      </header>

      <div class="story-cover-wrapper">
        <img src={story.coverImageUrl} alt={story.title} class="story-cover-img" />
      </div>

      {#if story.batakQuote}
        <div class="editorial-quote-card">
          <span class="quote-symbol">“</span>
          <p class="quote-batak-text">{story.batakQuote}</p>
          {#if story.quoteMeaning}
            <span class="quote-meaning-text">— {story.quoteMeaning}</span>
          {/if}
        </div>
      {/if}

      <article class="story-body-content">
        <p>{story.content}</p>
      </article>

      <div class="story-article-footer">
        <div class="share-box">
          <span class="share-title">Bagikan warisan budaya Batak ini:</span>
          <a
            href="https://wa.me/?text={encodeURIComponent(`Baca artikel budaya Batak menarik ini: ${story.title} di SamosirVerse`)}"
            target="_blank"
            rel="noopener noreferrer"
            class="btn btn-whatsapp btn-sm"
          >
            <span>💬 Bagikan ke WhatsApp</span>
          </a>
        </div>

        <div class="cta-box card-editorial">
          <h3>Ingin Menyaksikan Langsung Tradisi Ini?</h3>
          <p>Kunjungi destinasi dan perkampungan adat di Pulau Samosir atau ikuti workshop bersama perajin lokal.</p>
          <div class="cta-buttons-row">
            <a href="/destinasi" class="btn btn-forest btn-sm">Jelajahi Destinasi Terkait</a>
            <a href="/trip-planner" class="btn btn-primary btn-sm">Susun Rute Perjalanan</a>
          </div>
        </div>
      </div>
    </div>
  {/if}
</div>

<style>
  .story-detail-page {
    padding: 3.5rem 0 5rem;
  }

  .back-link {
    display: inline-block;
    font-size: 0.9rem;
    font-weight: 600;
    color: var(--color-muted);
    margin-bottom: 2rem;
    transition: color var(--transition-fast);
  }

  .back-link:hover {
    color: var(--color-forest);
  }

  .story-header {
    margin-bottom: 2rem;
  }

  .story-meta {
    display: flex;
    align-items: center;
    gap: 1rem;
    margin-bottom: 1rem;
  }

  .story-reading-time {
    font-size: 0.82rem;
    color: var(--color-muted);
    font-weight: 600;
  }

  .story-title {
    font-size: 2.75rem;
    line-height: 1.2;
    margin-bottom: 1.25rem;
  }

  .story-summary-lead {
    font-size: 1.2rem;
    line-height: 1.6;
    color: var(--color-muted);
    font-family: var(--font-serif);
    font-style: italic;
  }

  .story-cover-wrapper {
    width: 100%;
    height: 440px;
    border-radius: var(--radius-lg);
    overflow: hidden;
    margin-bottom: 2.5rem;
    box-shadow: var(--shadow-md);
  }

  .story-cover-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .editorial-quote-card {
    position: relative;
    background: linear-gradient(135deg, rgba(201, 110, 72, 0.1) 0%, rgba(246, 241, 231, 0.8) 100%);
    border-left: 4px solid var(--color-terracotta);
    border-radius: 0 var(--radius-md) var(--radius-md) 0;
    padding: 2rem 2.5rem;
    margin-bottom: 2.5rem;
  }

  .quote-symbol {
    font-family: var(--font-serif);
    font-size: 4rem;
    line-height: 1;
    color: rgba(201, 110, 72, 0.3);
    position: absolute;
    top: 10px;
    left: 15px;
  }

  .quote-batak-text {
    position: relative;
    z-index: 1;
    font-family: var(--font-serif);
    font-size: 1.35rem;
    font-style: italic;
    color: var(--color-forest);
    line-height: 1.4;
    margin-bottom: 0.5rem;
  }

  .quote-meaning-text {
    position: relative;
    z-index: 1;
    font-size: 0.95rem;
    color: var(--color-muted);
    display: block;
  }

  .story-body-content p {
    font-size: 1.12rem;
    line-height: 1.85;
    color: var(--color-charcoal);
    margin-bottom: 1.5rem;
  }

  .story-article-footer {
    margin-top: 3.5rem;
    padding-top: 2rem;
    border-top: 1px solid var(--color-border);
    display: flex;
    flex-direction: column;
    gap: 2.5rem;
  }

  .share-box {
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 1rem;
    padding: 1.25rem 1.5rem;
    background: var(--color-white);
    border: 1px solid var(--color-border);
    border-radius: var(--radius-md);
  }

  .share-title {
    font-weight: 600;
    font-size: 0.92rem;
    color: var(--color-forest);
  }

  .cta-box {
    padding: 2.25rem;
    background-color: var(--color-ivory-light);
    border: 1.5px solid var(--color-lake);
    text-align: center;
  }

  .cta-box h3 {
    font-size: 1.4rem;
    margin-bottom: 0.5rem;
  }

  .cta-box p {
    font-size: 0.95rem;
    color: var(--color-muted);
    max-width: 600px;
    margin: 0 auto 1.5rem;
  }

  .cta-buttons-row {
    display: flex;
    justify-content: center;
    gap: 1rem;
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

  @media (max-width: 640px) {
    .story-title {
      font-size: 2rem;
    }
    .story-cover-wrapper {
      height: 260px;
    }
    .cta-buttons-row {
      flex-direction: column;
    }
  }
</style>
