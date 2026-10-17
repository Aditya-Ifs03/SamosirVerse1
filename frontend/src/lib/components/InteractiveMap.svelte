<script lang="ts">
  import { onMount, onDestroy } from 'svelte';
  import { browser } from '$app/environment';
  import type { Destination } from '$lib/types';

  export let destinations: Destination[] = [];
  export let height = '520px';
  export let selectedCategory = '';

  let mapElement: HTMLElement;
  let mapInstance: any;
  let markersLayer: any;

  $: filteredDestinations = selectedCategory
    ? destinations.filter(d => d.category === selectedCategory)
    : destinations;

  $: if (mapInstance && markersLayer && filteredDestinations) {
    updateMarkers();
  }

  onMount(async () => {
    if (!browser) return;

    const L = (await import('leaflet')).default;

    // Center on Samosir Island
    mapInstance = L.map(mapElement, {
      center: [2.62, 98.75],
      zoom: 11,
      scrollWheelZoom: false
    });

    // Clean OpenStreetMap TileLayer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors | SamosirVerse',
      maxZoom: 18
    }).addTo(mapInstance);

    markersLayer = L.layerGroup().addTo(mapInstance);
    updateMarkers();
  });

  onDestroy(() => {
    if (mapInstance) {
      mapInstance.remove();
    }
  });

  async function updateMarkers() {
    if (!browser || !mapInstance || !markersLayer) return;
    const L = (await import('leaflet')).default;

    markersLayer.clearLayers();

    filteredDestinations.forEach(dest => {
      if (!dest.latitude || !dest.longitude) return;

      const isCulture = dest.category === 'BUDAYA_SEJARAH';
      const pinColor = isCulture ? '#C96E48' : '#123C36';

      const customIcon = L.divIcon({
        className: 'custom-map-pin',
        html: `
          <div style="
            background-color: ${pinColor};
            width: 34px;
            height: 34px;
            border-radius: 50% 50% 50% 0;
            transform: rotate(-45deg);
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 4px 10px rgba(0,0,0,0.3);
            border: 2px solid #FFFFFF;
          ">
            <span style="transform: rotate(45deg); font-size: 14px;">${isCulture ? '🏛️' : '🌿'}</span>
          </div>
        `,
        iconSize: [34, 34],
        iconAnchor: [17, 34],
        popupAnchor: [0, -34]
      });

      const popupContent = `
        <div style="font-family: var(--font-sans); min-width: 200px; padding: 4px;">
          <img src="${dest.imageUrl}" style="width: 100%; height: 100px; object-fit: cover; border-radius: 6px; margin-bottom: 8px;" />
          <h4 style="font-family: var(--font-serif); font-size: 15px; margin: 0 0 4px; color: #123C36;">${dest.name}</h4>
          <p style="font-size: 12px; color: #5E706B; margin: 0 0 8px;">${dest.subDistrict}, Samosir</p>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <span style="font-weight: 700; color: #C96E48; font-size: 13px;">${dest.ticketPrice === 0 ? 'Gratis' : 'Rp ' + dest.ticketPrice.toLocaleString('id-ID')}</span>
            <a href="/destinasi/${dest.slug}" style="background-color: #123C36; color: #FFFFFF; font-size: 11px; font-weight: 600; padding: 4px 10px; border-radius: 99px; text-decoration: none;">Lihat Detail &rarr;</a>
          </div>
        </div>
      `;

      L.marker([dest.latitude, dest.longitude], { icon: customIcon })
        .bindPopup(popupContent)
        .addTo(markersLayer);
    });
  }
</script>

<div class="interactive-map-container" style="height: {height};">
  <div bind:this={mapElement} class="map-view"></div>
</div>

<style>
  .interactive-map-container {
    width: 100%;
    border-radius: var(--radius-lg);
    overflow: hidden;
    box-shadow: var(--shadow-md);
    border: 1px solid var(--color-border);
    position: relative;
    background-color: var(--color-lake-light);
  }

  .map-view {
    width: 100%;
    height: 100%;
  }

  :global(.leaflet-popup-content-wrapper) {
    border-radius: var(--radius-md) !important;
    box-shadow: 0 8px 24px rgba(18, 60, 54, 0.16) !important;
    border: 1px solid #DCD3C5 !important;
  }
</style>
