<script lang="ts">
  import { onMount } from 'svelte';
  import { api } from '$lib/api';
  import type { TripPlanRequest, TripPlanResponse } from '$lib/types';
  import { showToast } from '$lib/stores/toast';

  let durationDays = 2;
  let travelStyle = 'BUDAYA';
  let budgetCategory = 'STANDAR';
  let groupType = 'PASANGAN';

  let tripPlan: TripPlanResponse | null = null;
  let loading = false;
  let activeDayTab = 1;

  async function handleGeneratePlan() {
    loading = true;
    try {
      const req: TripPlanRequest = {
        durationDays,
        travelStyle,
        budgetCategory,
        groupType
      };
      tripPlan = await api.generateTripPlan(req);
      activeDayTab = 1;
      showToast('Rencana perjalanan cerdas Samosir berhasil disusun!', 'success');
    } catch (err: any) {
      showToast(err.message || 'Gagal menyusun rencana perjalanan', 'error');
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    // Generate default recommended 2-day plan on load
    handleGeneratePlan();
  });

  function printPlan() {
    if (typeof window !== 'undefined') {
      window.print();
    }
  }
</script>

<svelte:head>
  <title>Smart Trip Planner Samosir — SamosirVerse</title>
</svelte:head>

<div class="trip-planner-page">
  <!-- Page Header -->
  <header class="planner-header text-center">
    <div class="container">
      <span class="badge badge-terracotta">Algoritma Berbasis Aturan Geospasial</span>
      <h1 class="header-title">Smart Trip Planner Pulau Samosir</h1>
      <p class="header-desc">
        Rencanakan liburan bermakna di Danau Toba dengan rute harian yang disusun secara cerdas berdasarkan jarak geografis, estimasi biaya realistis, dan etika budaya Batak Toba.
      </p>
    </div>
  </header>

  <div class="container">
    <div class="planner-main-grid">
      <!-- Left Column: Preference Form -->
      <aside class="planner-form-panel card-editorial">
        <h2 class="form-panel-title">Tentukan Preferensi Anda</h2>
        <div class="form-panel-divider"></div>

        <form on:submit|preventDefault={handleGeneratePlan}>
          <!-- 1. Durasi -->
          <div class="form-group">
            <label class="form-label" for="duration-select">Durasi Kunjungan</label>
            <div class="option-grid option-grid-3">
              <button
                type="button"
                class="option-pill {durationDays === 1 ? 'option-selected' : ''}"
                on:click={() => durationDays = 1}
              >
                1 Hari
              </button>
              <button
                type="button"
                class="option-pill {durationDays === 2 ? 'option-selected' : ''}"
                on:click={() => durationDays = 2}
              >
                2H 1M
              </button>
              <button
                type="button"
                class="option-pill {durationDays === 3 ? 'option-selected' : ''}"
                on:click={() => durationDays = 3}
              >
                3H 2M
              </button>
            </div>
          </div>

          <!-- 2. Gaya Perjalanan -->
          <div class="form-group">
            <label class="form-label" for="style-select">Gaya / Tema Utama</label>
            <div class="style-cards-grid">
              <button
                type="button"
                class="style-card-btn {travelStyle === 'BUDAYA' ? 'style-selected' : ''}"
                on:click={() => travelStyle = 'BUDAYA'}
              >
                <span class="style-icon">🏛️</span>
                <span class="style-name">Budaya & Sejarah</span>
              </button>

              <button
                type="button"
                class="style-card-btn {travelStyle === 'ALAM' ? 'style-selected' : ''}"
                on:click={() => travelStyle = 'ALAM'}
              >
                <span class="style-icon">🌄</span>
                <span class="style-name">Alam & Kaldera</span>
              </button>

              <button
                type="button"
                class="style-card-btn {travelStyle === 'SANTAI' ? 'style-selected' : ''}"
                on:click={() => travelStyle = 'SANTAI'}
              >
                <span class="style-icon">☕</span>
                <span class="style-name">Santai & Pesisir</span>
              </button>

              <button
                type="button"
                class="style-card-btn {travelStyle === 'KULINER_KREATIF' ? 'style-selected' : ''}"
                on:click={() => travelStyle = 'KULINER_KREATIF'}
              >
                <span class="style-icon">🧶</span>
                <span class="style-name">Kriya & Kuliner</span>
              </button>
            </div>
          </div>

          <!-- 3. Kategori Anggaran -->
          <div class="form-group">
            <label class="form-label" for="budget-select">Kategori Anggaran</label>
            <div class="option-grid option-grid-3">
              <button
                type="button"
                class="option-pill {budgetCategory === 'HEMAT' ? 'option-selected' : ''}"
                on:click={() => budgetCategory = 'HEMAT'}
              >
                Ekonomis
              </button>
              <button
                type="button"
                class="option-pill {budgetCategory === 'STANDAR' ? 'option-selected' : ''}"
                on:click={() => budgetCategory = 'STANDAR'}
              >
                Standar
              </button>
              <button
                type="button"
                class="option-pill {budgetCategory === 'PREMIUM' ? 'option-selected' : ''}"
                on:click={() => budgetCategory = 'PREMIUM'}
              >
                Premium
              </button>
            </div>
          </div>

          <!-- 4. Tipe Rombongan -->
          <div class="form-group">
            <label class="form-label" for="group-select">Tipe Rombongan</label>
            <select id="group-select" class="form-select" bind:value={groupType}>
              <option value="SOLO">Wisatawan Mandiri (Solo)</option>
              <option value="PASANGAN">Wisata Berpasangan</option>
              <option value="KELUARGA">Keluarga & Anak-anak</option>
              <option value="ROMBONGAN">Grup Wisata / Komunitas</option>
            </select>
          </div>

          <button type="submit" class="btn btn-primary w-full generate-submit-btn" disabled={loading}>
            {#if loading}
              <span>Menyusun Itinerary...</span>
            {:else}
              <span>⚡ Generate Itinerary Cerdas</span>
            {/if}
          </button>
        </form>
      </aside>

      <!-- Right Column: Itinerary Result -->
      <main class="planner-result-panel">
        {#if loading && !tripPlan}
          <div class="loading-box card-editorial">
            <div class="spinner"></div>
            <p>Algoritma Samosir sedang menyusun rute terbaik berdasarkan geolokasi...</p>
          </div>
        {:else if tripPlan}
          <!-- Plan Header Card -->
          <div class="plan-summary-card card-editorial">
            <div class="plan-meta-top">
              <span class="badge badge-forest">{tripPlan.durationDays} Hari Perjalanan</span>
              <span class="badge badge-lake">Gaya: {tripPlan.travelStyle}</span>
              <span class="badge badge-terracotta">Anggaran: {tripPlan.budgetCategory}</span>
            </div>

            <h2 class="plan-title">{tripPlan.planTitle}</h2>
            <p class="plan-desc">{tripPlan.summary}</p>

            <div class="plan-cost-banner">
              <div class="cost-info">
                <span class="cost-tag">Estimasi Total Biaya</span>
                <span class="cost-number">Rp {tripPlan.totalEstimatedCost.toLocaleString('id-ID')}</span>
                <span class="cost-sub">{tripPlan.costBreakdown}</span>
              </div>
              <button class="btn btn-secondary btn-sm print-btn" on:click={printPlan}>
                🖨️ Cetak / Simpan
              </button>
            </div>
          </div>

          <!-- Day Tabs -->
          <div class="day-tabs-wrapper">
            {#each tripPlan.days as day}
              <button
                class="day-tab-btn {activeDayTab === day.dayNumber ? 'day-tab-active' : ''}"
                on:click={() => activeDayTab = day.dayNumber}
              >
                <span class="day-tab-num">Hari {day.dayNumber}</span>
                <span class="day-tab-theme">{day.theme}</span>
              </button>
            {/each}
          </div>

          <!-- Active Day Timeline Activities -->
          {#each tripPlan.days as day}
            {#if activeDayTab === day.dayNumber}
              <div class="day-activities-timeline card-editorial animate-fade-in">
                <div class="timeline-day-header">
                  <h3 class="timeline-day-title">Hari ke-{day.dayNumber}: {day.theme}</h3>
                  <span class="timeline-day-count">{day.activities.length} Kegiatan Terjadwal</span>
                </div>

                <div class="timeline-items-list">
                  {#each day.activities as act, idx}
                    <div class="timeline-item">
                      <div class="timeline-node">
                        <span class="node-bullet">{idx + 1}</span>
                      </div>

                      <div class="timeline-content">
                        <div class="act-header-row">
                          <span class="act-time-pill badge badge-lake">⏱ {act.timeSlot}</span>
                          <span class="act-period-pill badge badge-forest">{act.period}</span>
                          {#if act.estimatedCost && act.estimatedCost > 0}
                            <span class="act-cost-pill">Rp {act.estimatedCost.toLocaleString('id-ID')}</span>
                          {/if}
                        </div>

                        <h4 class="act-title">{act.title}</h4>
                        <span class="act-loc">📍 {act.location}</span>
                        <p class="act-description">{act.description}</p>

                        {#if act.tips}
                          <div class="act-tip-box">
                            <span class="tip-label">💡 Tips Pemandu Budaya:</span>
                            <span class="tip-text">{act.tips}</span>
                          </div>
                        {/if}
                      </div>
                    </div>
                  {/each}
                </div>
              </div>
            {/if}
          {/each}

          <!-- Batak Cultural Etiquette Guidelines -->
          {#if tripPlan.culturalEtiquetteTips && tripPlan.culturalEtiquetteTips.length > 0}
            <div class="etiquette-card card-editorial">
              <div class="etiquette-header">
                <span class="etiquette-icon">🌿</span>
                <h3 class="etiquette-title">Etika Budaya & Kearifan Lokal Samosir</h3>
              </div>
              <ul class="etiquette-list">
                {#each tripPlan.culturalEtiquetteTips as tip}
                  <li>
                    <span class="list-bullet">◆</span>
                    <span>{tip}</span>
                  </li>
                {/each}
              </ul>
            </div>
          {/if}
        {/if}
      </main>
    </div>
  </div>
</div>

<style>
  .trip-planner-page {
    padding-bottom: 5rem;
  }

  .planner-header {
    background: linear-gradient(180deg, rgba(201, 110, 72, 0.08) 0%, transparent 100%);
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

  .planner-main-grid {
    display: grid;
    grid-template-columns: 360px 1fr;
    gap: 2.5rem;
    align-items: flex-start;
  }

  /* Form Panel */
  .planner-form-panel {
    padding: 2rem;
    position: sticky;
    top: 90px;
    background-color: var(--color-white);
  }

  .form-panel-title {
    font-size: 1.35rem;
    color: var(--color-forest);
  }

  .form-panel-divider {
    height: 1px;
    background-color: var(--color-border);
    margin: 1rem 0 1.5rem;
  }

  .option-grid {
    display: grid;
    gap: 0.5rem;
  }

  .option-grid-3 {
    grid-template-columns: repeat(3, 1fr);
  }

  .option-pill {
    padding: 0.6rem 0.5rem;
    font-size: 0.85rem;
    font-weight: 700;
    border-radius: var(--radius-sm);
    border: 1.5px solid var(--color-border);
    background-color: var(--color-white);
    color: var(--color-forest);
    transition: all var(--transition-fast);
    text-align: center;
  }

  .option-pill:hover {
    border-color: var(--color-forest);
  }

  .option-selected {
    background-color: var(--color-forest) !important;
    color: var(--color-white) !important;
    border-color: var(--color-forest) !important;
    box-shadow: 0 4px 10px rgba(18, 60, 54, 0.2);
  }

  .style-cards-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 0.6rem;
  }

  .style-card-btn {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0.85rem 0.5rem;
    border: 1.5px solid var(--color-border);
    border-radius: var(--radius-md);
    background-color: var(--color-white);
    text-align: center;
    gap: 0.35rem;
    transition: all var(--transition-fast);
  }

  .style-icon {
    font-size: 1.35rem;
  }

  .style-name {
    font-size: 0.78rem;
    font-weight: 700;
    color: var(--color-charcoal);
  }

  .style-card-btn:hover {
    border-color: var(--color-terracotta);
  }

  .style-selected {
    border-color: var(--color-terracotta) !important;
    background-color: rgba(201, 110, 72, 0.08) !important;
    box-shadow: 0 4px 12px rgba(201, 110, 72, 0.15);
  }

  .style-selected .style-name {
    color: var(--color-terracotta);
  }

  .generate-submit-btn {
    margin-top: 1rem;
    padding: 0.85rem;
  }

  .w-full {
    width: 100%;
  }

  /* Result Panel */
  .planner-result-panel {
    display: flex;
    flex-direction: column;
    gap: 2rem;
  }

  .plan-summary-card {
    padding: 2.25rem;
    background-color: var(--color-white);
  }

  .plan-meta-top {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
    margin-bottom: 0.85rem;
  }

  .plan-title {
    font-size: 2rem;
    color: var(--color-forest);
    margin-bottom: 0.6rem;
  }

  .plan-desc {
    font-size: 1rem;
    color: var(--color-muted);
    line-height: 1.6;
    margin-bottom: 1.75rem;
  }

  .plan-cost-banner {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 1.25rem 1.75rem;
    background: var(--color-ivory-light);
    border: 1px solid var(--color-border);
    border-radius: var(--radius-md);
    gap: 1.5rem;
  }

  .cost-tag {
    display: block;
    font-size: 0.72rem;
    text-transform: uppercase;
    color: var(--color-muted);
    font-weight: 700;
    margin-bottom: 0.2rem;
  }

  .cost-number {
    display: block;
    font-size: 1.8rem;
    font-weight: 800;
    color: var(--color-terracotta);
    line-height: 1.1;
  }

  .cost-sub {
    display: block;
    font-size: 0.82rem;
    color: var(--color-muted);
    margin-top: 0.35rem;
  }

  .day-tabs-wrapper {
    display: flex;
    gap: 0.75rem;
    overflow-x: auto;
  }

  .day-tab-btn {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 1rem 1.25rem;
    background-color: var(--color-white);
    border: 1.5px solid var(--color-border);
    border-radius: var(--radius-md);
    text-align: left;
    transition: all var(--transition-fast);
  }

  .day-tab-num {
    font-size: 0.75rem;
    font-weight: 800;
    color: var(--color-terracotta);
    letter-spacing: 0.05em;
    text-transform: uppercase;
  }

  .day-tab-theme {
    font-family: var(--font-serif);
    font-size: 1rem;
    font-weight: 600;
    color: var(--color-forest);
    line-height: 1.3;
    margin-top: 0.2rem;
  }

  .day-tab-active {
    border-color: var(--color-forest) !important;
    background-color: var(--color-ivory) !important;
    box-shadow: 0 4px 14px rgba(18, 60, 54, 0.12);
  }

  .day-activities-timeline {
    padding: 2.25rem;
    background-color: var(--color-white);
  }

  .timeline-day-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 1.25rem;
    border-bottom: 1px solid var(--color-border);
    margin-bottom: 2rem;
  }

  .timeline-day-title {
    font-size: 1.35rem;
    color: var(--color-forest);
  }

  .timeline-day-count {
    font-size: 0.82rem;
    color: var(--color-muted);
    font-weight: 600;
  }

  .timeline-items-list {
    display: flex;
    flex-direction: column;
    gap: 2rem;
    position: relative;
  }

  .timeline-items-list::before {
    content: '';
    position: absolute;
    top: 15px;
    bottom: 15px;
    left: 17px;
    width: 2px;
    background-color: var(--color-border);
  }

  .timeline-item {
    display: flex;
    gap: 1.5rem;
    position: relative;
    z-index: 1;
  }

  .timeline-node {
    flex-shrink: 0;
  }

  .node-bullet {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    background-color: var(--color-forest);
    color: var(--color-white);
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: 700;
    font-size: 0.9rem;
    border: 3px solid var(--color-ivory);
    box-shadow: 0 2px 6px rgba(0,0,0,0.15);
  }

  .timeline-content {
    flex: 1;
    background-color: var(--color-ivory-light);
    border: 1px solid var(--color-border);
    border-radius: var(--radius-md);
    padding: 1.5rem;
  }

  .act-header-row {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    gap: 0.5rem;
    margin-bottom: 0.6rem;
  }

  .act-cost-pill {
    margin-left: auto;
    font-weight: 700;
    color: var(--color-terracotta);
    font-size: 0.88rem;
  }

  .act-title {
    font-size: 1.25rem;
    color: var(--color-forest);
    margin-bottom: 0.35rem;
  }

  .act-loc {
    display: block;
    font-size: 0.82rem;
    color: var(--color-muted);
    font-weight: 600;
    margin-bottom: 0.75rem;
  }

  .act-description {
    font-size: 0.92rem;
    color: var(--color-charcoal);
    line-height: 1.6;
    margin-bottom: 1rem;
  }

  .act-tip-box {
    background-color: var(--color-ivory);
    border-left: 3px solid var(--color-terracotta);
    padding: 0.75rem 1rem;
    border-radius: 0 var(--radius-sm) var(--radius-sm) 0;
    font-size: 0.82rem;
  }

  .tip-label {
    font-weight: 700;
    color: var(--color-forest);
    margin-right: 0.35rem;
  }

  .tip-text {
    color: var(--color-charcoal);
  }

  /* Etiquette Card */
  .etiquette-card {
    padding: 2rem;
    background-color: var(--color-ivory-light);
    border: 1.5px solid var(--color-lake);
  }

  .etiquette-header {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    margin-bottom: 1.25rem;
  }

  .etiquette-icon {
    font-size: 1.6rem;
  }

  .etiquette-title {
    font-size: 1.35rem;
    color: var(--color-forest);
  }

  .etiquette-list {
    list-style: none;
    display: flex;
    flex-direction: column;
    gap: 0.85rem;
  }

  .etiquette-list li {
    display: flex;
    align-items: flex-start;
    gap: 0.75rem;
    font-size: 0.92rem;
    line-height: 1.6;
    color: var(--color-charcoal);
  }

  .list-bullet {
    color: var(--color-terracotta);
    font-size: 0.8rem;
    flex-shrink: 0;
    margin-top: 0.2rem;
  }

  .loading-box {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 5rem 1rem;
    text-align: center;
    color: var(--color-muted);
    gap: 1rem;
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

  @media (max-width: 992px) {
    .planner-main-grid {
      grid-template-columns: 1fr;
    }
    .planner-form-panel {
      position: static;
    }
  }

  @media (max-width: 640px) {
    .header-title {
      font-size: 2rem;
    }
    .plan-cost-banner {
      flex-direction: column;
      align-items: flex-start;
    }
    .print-btn {
      width: 100%;
    }
  }
</style>
