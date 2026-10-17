<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { Product } from '$lib/types';
  import ProductCard from '$lib/components/ProductCard.svelte';

  let products: Product[] = [];
  let loading = true;
  let selectedCategory = '';
  let searchKeyword = '';

  const categories = [
    { value: '', label: 'Semua Kategori' },
    { value: 'TENUN_ULOS', label: '🧶 Tenun Ulos Asli' },
    { value: 'KERAJINAN_GORGA', label: '🪵 Ukiran Gorga Batak' },
    { value: 'KOPI_REMPAH', label: '☕ Kopi Arabica Pusuk Buhit' },
    { value: 'KULINER_KHAS', label: '🌶️ Olahan Sambal Andaliman' }
  ];

  async function loadProducts() {
    loading = true;
    try {
      products = await api.getProducts(selectedCategory, undefined, searchKeyword);
    } catch (err) {
      console.error(err);
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    loadProducts();
  });
</script>

<svelte:head>
  <title>Produk UMKM & Cinderamata Otentik Samosir — SamosirVerse</title>
</svelte:head>

<div class="produk-page">
  <header class="page-header text-center">
    <div class="container">
      <span class="badge badge-forest">Ekonomi Kerakyatan & Kerajinan Tangan</span>
      <h1 class="header-title">Katalog Produk Kreator Samosir</h1>
      <p class="header-desc">
        Dapatkan kain ulos tenun tangan pewarna alami, plakat gorga Batak Singa-singa kayu ingul, kopi specialty Pusuk Buhit, dan sambal andaliman segar langsung dari perajin lokal tanpa perantara.
      </p>
    </div>
  </header>

  <div class="container">
    <div class="filter-card card-editorial">
      <div class="filter-grid">
        <input
          type="text"
          class="form-input"
          placeholder="Cari nama produk kerajinan atau cinderamata..."
          bind:value={searchKeyword}
          on:input={loadProducts}
        />
        <div class="category-buttons">
          {#each categories as cat}
            <button
              class="cat-pill {selectedCategory === cat.value ? 'cat-active' : ''}"
              on:click={() => { selectedCategory = cat.value; loadProducts(); }}
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
        <p>Menyiapkan etalase karya perajin...</p>
      </div>
    {:else if products.length === 0}
      <div class="state-container">
        <p>Tidak ada produk yang cocok dengan filter Anda.</p>
        <button on:click={() => { selectedCategory = ''; searchKeyword = ''; loadProducts(); }} class="btn btn-secondary btn-sm">Reset Filter</button>
      </div>
    {:else}
      <div class="products-grid">
        {#each products as product (product.id)}
          <ProductCard {product} />
        {/each}
      </div>
    {/if}
  </div>
</div>

<style>
  .produk-page {
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

  .category-buttons {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
  }

  .cat-pill {
    padding: 0.45rem 1rem;
    border-radius: var(--radius-full);
    border: 1.5px solid var(--color-border);
    background-color: var(--color-white);
    font-size: 0.85rem;
    font-weight: 600;
    color: var(--color-forest);
    transition: all var(--transition-fast);
  }

  .cat-pill:hover {
    border-color: var(--color-forest);
  }

  .cat-active {
    background-color: var(--color-forest) !important;
    color: var(--color-white) !important;
    border-color: var(--color-forest) !important;
  }

  .products-grid {
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
    .products-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 640px) {
    .products-grid {
      grid-template-columns: 1fr;
    }
    .header-title {
      font-size: 2rem;
    }
  }
</style>
