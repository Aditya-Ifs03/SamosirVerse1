<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { Creator, Product } from '$lib/types';
  import CreatorCard from '$lib/components/CreatorCard.svelte';
  import ProductCard from '$lib/components/ProductCard.svelte';

  let creators: Creator[] = [];
  let products: Product[] = [];
  let loading = true;
  let activeTab: 'kreator' | 'produk' = 'kreator';
  let searchKeyword = '';

  async function loadData() {
    loading = true;
    try {
      const [creatList, prodList] = await Promise.all([
        api.getCreators('', searchKeyword),
        api.getProducts('', undefined, searchKeyword)
      ]);
      creators = creatList;
      products = prodList;
    } catch (err) {
      console.error(err);
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    loadData();
  });
</script>

<svelte:head>
  <title>Kreator Lokal & Produk UMKM Samosir — SamosirVerse</title>
</svelte:head>

<div class="kreator-page">
  <header class="page-header text-center">
    <div class="container">
      <span class="badge badge-lake">Direktori Pengrajin Asli</span>
      <h1 class="header-title">Maestro Seni & Produk UMKM Samosir</h1>
      <p class="header-desc">
        Temui para penenun ulos tradisional pewarna alam, pemahat gorga Batak, petani kopi Pusuk Buhit, dan pengolah kuliner rempah andaliman dengan nomor WhatsApp yang telah diverifikasi langsung.
      </p>

      <!-- Sub Tab Switcher -->
      <div class="tab-switcher">
        <button
          class="tab-btn {activeTab === 'kreator' ? 'tab-active' : ''}"
          on:click={() => activeTab = 'kreator'}
        >
          <span>👤 Profil Kreator & Maestro ({creators.length})</span>
        </button>
        <button
          class="tab-btn {activeTab === 'produk' ? 'tab-active' : ''}"
          on:click={() => activeTab = 'produk'}
        >
          <span>🎁 Katalog Produk UMKM ({products.length})</span>
        </button>
      </div>
    </div>
  </header>

  <div class="container">
    <div class="search-box card-editorial">
      <input
        type="text"
        class="form-input"
        placeholder={activeTab === 'kreator' ? 'Cari nama maestro, desa, atau jenis keahlian...' : 'Cari nama produk, ulos, kopi, kerajinan...'}
        bind:value={searchKeyword}
        on:input={loadData}
      />
    </div>

    {#if loading}
      <div class="state-container">
        <div class="spinner"></div>
        <p>Memuat direktori kreator dan produk...</p>
      </div>
    {:else if activeTab === 'kreator'}
      {#if creators.length === 0}
        <div class="state-container">
          <p>Tidak ada kreator yang sesuai pencarian.</p>
        </div>
      {:else}
        <div class="cards-grid">
          {#each creators as creator (creator.id)}
            <CreatorCard {creator} />
          {/each}
        </div>
      {/if}
    {:else}
      {#if products.length === 0}
        <div class="state-container">
          <p>Tidak ada produk UMKM yang sesuai pencarian.</p>
        </div>
      {:else}
        <div class="cards-grid">
          {#each products as product (product.id)}
            <ProductCard {product} />
          {/each}
        </div>
      {/if}
    {/if}
  </div>
</div>

<style>
  .kreator-page {
    padding-bottom: 5rem;
  }

  .page-header {
    background: linear-gradient(180deg, rgba(18, 60, 54, 0.08) 0%, transparent 100%);
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
    margin-bottom: 2rem;
  }

  .tab-switcher {
    display: inline-flex;
    background-color: var(--color-white);
    padding: 0.35rem;
    border-radius: var(--radius-full);
    border: 1.5px solid var(--color-border);
    gap: 0.35rem;
  }

  .tab-btn {
    padding: 0.65rem 1.5rem;
    font-size: 0.92rem;
    font-weight: 700;
    border-radius: var(--radius-full);
    color: var(--color-forest);
    transition: all var(--transition-fast);
  }

  .tab-active {
    background-color: var(--color-forest);
    color: var(--color-white);
    box-shadow: 0 4px 12px rgba(18, 60, 54, 0.2);
  }

  .search-box {
    padding: 1.25rem;
    margin-bottom: 2.5rem;
  }

  .cards-grid {
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
    .cards-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 640px) {
    .cards-grid {
      grid-template-columns: 1fr;
    }
    .header-title {
      font-size: 2rem;
    }
    .tab-switcher {
      flex-direction: column;
      border-radius: var(--radius-md);
      width: 100%;
    }
    .tab-btn {
      border-radius: var(--radius-sm);
    }
  }
</style>
