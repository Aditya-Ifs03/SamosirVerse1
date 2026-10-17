<script lang="ts">
  import { onMount } from 'svelte';
  import { goto } from '$app/navigation';
  import { api } from '$lib/api';
  import { authStore, clearSession } from '$lib/stores/auth';
  import { showToast } from '$lib/stores/toast';
  import type { Destination, Creator, Product, CulturalStory, Experience, DashboardStats } from '$lib/types';

  let activeTab: 'destinasi' | 'kreator' | 'produk' | 'cerita' | 'agenda' = 'destinasi';
  let stats: DashboardStats = {
    totalDestinations: 0,
    totalCreators: 0,
    totalProducts: 0,
    totalStories: 0,
    totalExperiences: 0
  };
  let loading = true;

  // Data Lists
  let destinations: Destination[] = [];
  let creators: Creator[] = [];
  let products: Product[] = [];
  let stories: CulturalStory[] = [];
  let experiences: Experience[] = [];

  // Modal State
  let modalOpen = false;
  let modalMode: 'create' | 'edit' = 'create';
  let modalEntity: 'destinasi' | 'kreator' | 'produk' | 'cerita' | 'agenda' = 'destinasi';
  let editingId: number | null = null;

  // Form State
  let destForm = {
    name: '',
    category: 'BUDAYA_SEJARAH',
    subDistrict: 'Simanindo',
    description: '',
    shortDescription: '',
    culturalSignificance: '',
    ticketPrice: 10000,
    estimatedDurationHours: 1.5,
    openingHours: '08:00 - 18:00 WIB',
    latitude: 2.65,
    longitude: 98.85,
    imageUrl: '',
    facilities: '',
    isFeatured: false
  };

  let creatorForm = {
    name: '',
    craftType: 'Penenun Ulos Tradisional',
    village: 'Desa Lumban Suhi-Suhi',
    bio: '',
    whatsappNumber: '+628123456789',
    isWhatsappVerified: true,
    profileImageUrl: '',
    yearsOfExperience: 5,
    awards: ''
  };

  let productForm = {
    creatorId: 1,
    name: '',
    category: 'TENUN_ULOS',
    price: 150000,
    description: '',
    imageUrl: '',
    stockStatus: 'TERSEDIA',
    isAuthenticHandcrafted: true
  };

  let storyForm = {
    title: '',
    category: 'FILOSOFI',
    summary: '',
    content: '',
    batakQuote: '',
    quoteMeaning: '',
    readingTimeMinutes: 5,
    coverImageUrl: ''
  };

  let experienceForm = {
    title: '',
    type: 'WORKSHOP',
    location: 'Samosir',
    eventDate: 'Setiap Akhir Pekan',
    duration: '3 Jam',
    price: 50000,
    description: '',
    organizerName: 'Komunitas Lokal Samosir',
    whatsappBooking: '+628123456789',
    imageUrl: '',
    maxParticipants: 15
  };

  onMount(async () => {
    if (!$authStore.isAuthenticated) {
      showToast('Silakan login terlebih dahulu untuk mengakses dashboard.', 'info');
      goto('/admin/login');
      return;
    }
    await loadDashboardData();
  });

  async function loadDashboardData() {
    loading = true;
    try {
      const [s, dList, cList, pList, stList, eList] = await Promise.all([
        api.getAdminStats(),
        api.getDestinations(),
        api.getCreators(),
        api.getProducts(),
        api.getCulturalStories(),
        api.getExperiences()
      ]);
      stats = s;
      destinations = dList;
      creators = cList;
      products = pList;
      stories = stList;
      experiences = eList;
      // sync creatorId in productForm default
      if (creators.length > 0) {
        productForm.creatorId = creators[0].id;
      }
    } catch (err: any) {
      showToast(err.message || 'Gagal memuat data dashboard', 'error');
    } finally {
      loading = false;
    }
  }

  function handleLogout() {
    clearSession();
    showToast('Berhasil keluar dari dashboard.', 'info');
    goto('/admin/login');
  }

  // --- Modal Openers ---
  function openCreateModal(entity: typeof activeTab) {
    modalMode = 'create';
    modalEntity = entity;
    editingId = null;

    if (entity === 'destinasi') {
      destForm = {
        name: '',
        category: 'BUDAYA_SEJARAH',
        subDistrict: 'Simanindo',
        description: '',
        shortDescription: '',
        culturalSignificance: '',
        ticketPrice: 10000,
        estimatedDurationHours: 1.5,
        openingHours: '08:00 - 18:00 WIB',
        latitude: 2.65,
        longitude: 98.85,
        imageUrl: 'https://images.unsplash.com/photo-1596422846543-75c6fc197f07?auto=format&fit=crop&w=1200&q=80',
        facilities: 'Area Parkir, Toilet, Gazebo',
        isFeatured: false
      };
    } else if (entity === 'kreator') {
      creatorForm = {
        name: '',
        craftType: 'Penenun Ulos Tradisional',
        village: 'Desa Lumban Suhi-Suhi',
        bio: '',
        whatsappNumber: '+628123456789',
        isWhatsappVerified: true,
        profileImageUrl: 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&w=600&q=80',
        yearsOfExperience: 5,
        awards: 'Sertifikasi Kriya Daerah'
      };
    } else if (entity === 'produk') {
      productForm = {
        creatorId: creators.length > 0 ? creators[0].id : 1,
        name: '',
        category: 'TENUN_ULOS',
        price: 150000,
        description: '',
        imageUrl: 'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=800&q=80',
        stockStatus: 'TERSEDIA',
        isAuthenticHandcrafted: true
      };
    } else if (entity === 'cerita') {
      storyForm = {
        title: '',
        category: 'FILOSOFI',
        summary: '',
        content: '',
        batakQuote: '',
        quoteMeaning: '',
        readingTimeMinutes: 5,
        coverImageUrl: 'https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?auto=format&fit=crop&w=1200&q=80'
      };
    } else if (entity === 'agenda') {
      experienceForm = {
        title: '',
        type: 'WORKSHOP',
        location: 'Samosir',
        eventDate: 'Setiap Hari Sabtu',
        duration: '3 Jam',
        price: 50000,
        description: '',
        organizerName: 'Sanggar Budaya Batak',
        whatsappBooking: '+628123456789',
        imageUrl: 'https://images.unsplash.com/photo-1607344645866-009c320c5ab8?auto=format&fit=crop&w=1200&q=80',
        maxParticipants: 15
      };
    }
    modalOpen = true;
  }

  function openEditModal(entity: typeof activeTab, item: any) {
    modalMode = 'edit';
    modalEntity = entity;
    editingId = item.id;

    if (entity === 'destinasi') {
      destForm = {
        name: item.name,
        category: item.category,
        subDistrict: item.subDistrict,
        description: item.description,
        shortDescription: item.shortDescription,
        culturalSignificance: item.culturalSignificance || '',
        ticketPrice: item.ticketPrice,
        estimatedDurationHours: item.estimatedDurationHours,
        openingHours: item.openingHours,
        latitude: item.latitude,
        longitude: item.longitude,
        imageUrl: item.imageUrl,
        facilities: item.facilities || '',
        isFeatured: item.isFeatured
      };
    } else if (entity === 'kreator') {
      creatorForm = {
        name: item.name,
        craftType: item.craftType,
        village: item.village,
        bio: item.bio,
        whatsappNumber: item.whatsappNumber,
        isWhatsappVerified: item.isWhatsappVerified,
        profileImageUrl: item.profileImageUrl,
        yearsOfExperience: item.yearsOfExperience,
        awards: item.awards || ''
      };
    } else if (entity === 'produk') {
      productForm = {
        creatorId: item.creator?.id || (creators[0]?.id ?? 1),
        name: item.name,
        category: item.category,
        price: item.price,
        description: item.description,
        imageUrl: item.imageUrl,
        stockStatus: item.stockStatus,
        isAuthenticHandcrafted: item.isAuthenticHandcrafted
      };
    } else if (entity === 'cerita') {
      storyForm = {
        title: item.title,
        category: item.category,
        summary: item.summary,
        content: item.content,
        batakQuote: item.batakQuote || '',
        quoteMeaning: item.quoteMeaning || '',
        readingTimeMinutes: item.readingTimeMinutes,
        coverImageUrl: item.coverImageUrl
      };
    } else if (entity === 'agenda') {
      experienceForm = {
        title: item.title,
        type: item.type,
        location: item.location,
        eventDate: item.eventDate,
        duration: item.duration,
        price: item.price,
        description: item.description,
        organizerName: item.organizerName,
        whatsappBooking: item.whatsappBooking,
        imageUrl: item.imageUrl,
        maxParticipants: item.maxParticipants
      };
    }
    modalOpen = true;
  }

  async function handleSave() {
    try {
      if (modalEntity === 'destinasi') {
        if (modalMode === 'create') {
          const created = await api.createDestination(destForm);
          destinations = [created, ...destinations];
          showToast('Destinasi baru berhasil ditambahkan!', 'success');
        } else if (editingId) {
          const updated = await api.updateDestination(editingId, destForm);
          destinations = destinations.map(d => d.id === editingId ? updated : d);
          showToast('Destinasi berhasil diperbarui!', 'success');
        }
      } else if (modalEntity === 'kreator') {
        if (modalMode === 'create') {
          const created = await api.createCreator(creatorForm);
          creators = [created, ...creators];
          showToast('Kreator lokal baru berhasil ditambahkan!', 'success');
        } else if (editingId) {
          const updated = await api.updateCreator(editingId, creatorForm);
          creators = creators.map(c => c.id === editingId ? updated : c);
          showToast('Profil kreator berhasil diperbarui!', 'success');
        }
      } else if (modalEntity === 'produk') {
        if (modalMode === 'create') {
          const created = await api.createProduct(productForm);
          products = [created, ...products];
          showToast('Produk UMKM berhasil ditambahkan!', 'success');
        } else if (editingId) {
          const updated = await api.updateProduct(editingId, productForm);
          products = products.map(p => p.id === editingId ? updated : p);
          showToast('Produk UMKM berhasil diperbarui!', 'success');
        }
      } else if (modalEntity === 'cerita') {
        if (modalMode === 'create') {
          const created = await api.createStory(storyForm);
          stories = [created, ...stories];
          showToast('Cerita budaya berhasil ditambahkan!', 'success');
        } else if (editingId) {
          const updated = await api.updateStory(editingId, storyForm);
          stories = stories.map(s => s.id === editingId ? updated : s);
          showToast('Cerita budaya berhasil diperbarui!', 'success');
        }
      } else if (modalEntity === 'agenda') {
        if (modalMode === 'create') {
          const created = await api.createExperience(experienceForm);
          experiences = [created, ...experiences];
          showToast('Agenda/pengalaman berhasil ditambahkan!', 'success');
        } else if (editingId) {
          const updated = await api.updateExperience(editingId, experienceForm);
          experiences = experiences.map(e => e.id === editingId ? updated : e);
          showToast('Agenda/pengalaman berhasil diperbarui!', 'success');
        }
      }
      modalOpen = false;
      // Refresh stats
      stats = await api.getAdminStats();
    } catch (err: any) {
      showToast(err.message || 'Gagal menyimpan perubahan', 'error');
    }
  }

  async function handleDelete(entity: typeof activeTab, id: number, name: string) {
    if (!confirm(`Apakah Anda yakin ingin menghapus "${name}"? Tindakan ini tidak dapat dibatalkan.`)) {
      return;
    }

    try {
      if (entity === 'destinasi') {
        await api.deleteDestination(id);
        destinations = destinations.filter(d => d.id !== id);
      } else if (entity === 'kreator') {
        await api.deleteCreator(id);
        creators = creators.filter(c => c.id !== id);
      } else if (entity === 'produk') {
        await api.deleteProduct(id);
        products = products.filter(p => p.id !== id);
      } else if (entity === 'cerita') {
        await api.deleteStory(id);
        stories = stories.filter(s => s.id !== id);
      } else if (entity === 'agenda') {
        await api.deleteExperience(id);
        experiences = experiences.filter(e => e.id !== id);
      }
      showToast(`"${name}" berhasil dihapus.`, 'info');
      stats = await api.getAdminStats();
    } catch (err: any) {
      showToast(err.message || 'Gagal menghapus data', 'error');
    }
  }
</script>

<svelte:head>
  <title>Admin Dashboard & CRUD — SamosirVerse</title>
</svelte:head>

<div class="admin-dashboard-page">
  <!-- Dashboard Top Bar -->
  <header class="dashboard-topbar">
    <div class="container topbar-inner">
      <div class="topbar-left">
        <h1 class="dashboard-title">Dashboard Manajemen SamosirVerse</h1>
        <span class="user-greeting">Halo, {$authStore.user?.fullName || 'Administrator'} &bull; Role: {$authStore.user?.role || 'ROLE_ADMIN'}</span>
      </div>

      <div class="topbar-right">
        <a href="/" class="btn btn-secondary btn-sm" target="_blank">
          <span>🌐 Pratinjau Web Publik</span>
        </a>
        <button on:click={handleLogout} class="btn btn-outline-terracotta btn-sm">
          <span>🚪 Keluar</span>
        </button>
      </div>
    </div>
  </header>

  <div class="container dashboard-container">
    <!-- METRICS CARDS -->
    <section class="metrics-grid">
      <div class="metric-card card-editorial">
        <div class="metric-icon">🏛️</div>
        <div class="metric-info">
          <span class="metric-lbl">Total Destinasi</span>
          <span class="metric-val">{stats.totalDestinations || destinations.length}</span>
        </div>
      </div>

      <div class="metric-card card-editorial">
        <div class="metric-icon">👤</div>
        <div class="metric-info">
          <span class="metric-lbl">Kreator & Pengrajin</span>
          <span class="metric-val">{stats.totalCreators || creators.length}</span>
        </div>
      </div>

      <div class="metric-card card-editorial">
        <div class="metric-icon">🎁</div>
        <div class="metric-info">
          <span class="metric-lbl">Produk UMKM</span>
          <span class="metric-val">{stats.totalProducts || products.length}</span>
        </div>
      </div>

      <div class="metric-card card-editorial">
        <div class="metric-icon">📖</div>
        <div class="metric-info">
          <span class="metric-lbl">Cerita Budaya</span>
          <span class="metric-val">{stats.totalStories || stories.length}</span>
        </div>
      </div>

      <div class="metric-card card-editorial">
        <div class="metric-icon">📅</div>
        <div class="metric-info">
          <span class="metric-lbl">Pengalaman & Agenda</span>
          <span class="metric-val">{stats.totalExperiences || experiences.length}</span>
        </div>
      </div>
    </section>

    <!-- MODULE TABS -->
    <div class="module-nav">
      <button
        class="module-btn {activeTab === 'destinasi' ? 'module-active' : ''}"
        on:click={() => activeTab = 'destinasi'}
      >
        🏛️ Destinasi Wisata
      </button>
      <button
        class="module-btn {activeTab === 'kreator' ? 'module-active' : ''}"
        on:click={() => activeTab = 'kreator'}
      >
        👤 Kreator & Maestro
      </button>
      <button
        class="module-btn {activeTab === 'produk' ? 'module-active' : ''}"
        on:click={() => activeTab = 'produk'}
      >
        🎁 Produk UMKM
      </button>
      <button
        class="module-btn {activeTab === 'cerita' ? 'module-active' : ''}"
        on:click={() => activeTab = 'cerita'}
      >
        📖 Cerita Budaya Batak
      </button>
      <button
        class="module-btn {activeTab === 'agenda' ? 'module-active' : ''}"
        on:click={() => activeTab = 'agenda'}
      >
        📅 Agenda & Festival
      </button>
    </div>

    <!-- MAIN DATA TABLE SECTION -->
    <div class="crud-panel card-editorial">
      <div class="crud-panel-header">
        <div>
          <h2 class="panel-title">
            {#if activeTab === 'destinasi'} Manajemen Destinasi Wisata Samosir
            {:else if activeTab === 'kreator'} Kurasi Kreator & Pengrajin Samosir
            {:else if activeTab === 'produk'} Katalog Produk UMKM Samosir
            {:else if activeTab === 'cerita'} Artikel Warisan Budaya Batak
            {:else} Agenda & Pengalaman Turis
            {/if}
          </h2>
          <span class="panel-subtitle">Kelola dan perbarui data real-time yang langsung tayang pada portal publik.</span>
        </div>

        <button class="btn btn-primary btn-sm" on:click={() => openCreateModal(activeTab)}>
          <span>➕ Tambah Data Baru</span>
        </button>
      </div>

      {#if loading}
        <div class="table-loading">
          <div class="spinner"></div>
          <p>Memuat data tabel...</p>
        </div>
      {:else}
        <!-- 1. DESTINATIONS TABLE -->
        {#if activeTab === 'destinasi'}
          <div class="table-responsive">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Foto</th>
                  <th>Nama Destinasi</th>
                  <th>Kategori</th>
                  <th>Kecamatan</th>
                  <th>Tiket</th>
                  <th>Rating</th>
                  <th>Unggulan</th>
                  <th>Aksi</th>
                </tr>
              </thead>
              <tbody>
                {#each destinations as d}
                  <tr>
                    <td>
                      <img src={d.imageUrl} alt={d.name} class="table-thumb" />
                    </td>
                    <td>
                      <strong>{d.name}</strong>
                    </td>
                    <td>
                      <span class="badge badge-lake">{d.category}</span>
                    </td>
                    <td>{d.subDistrict}</td>
                    <td>Rp {d.ticketPrice.toLocaleString('id-ID')}</td>
                    <td>★ {d.rating}</td>
                    <td>{d.isFeatured ? '⭐ Ya' : 'Tidak'}</td>
                    <td>
                      <div class="action-buttons">
                        <button class="btn-action btn-edit" on:click={() => openEditModal('destinasi', d)} title="Edit">
                          ✏️
                        </button>
                        <button class="btn-action btn-delete" on:click={() => handleDelete('destinasi', d.id, d.name)} title="Hapus">
                          🗑️
                        </button>
                      </div>
                    </td>
                  </tr>
                {/each}
              </tbody>
            </table>
          </div>

        <!-- 2. CREATORS TABLE -->
        {:else if activeTab === 'kreator'}
          <div class="table-responsive">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Avatar</th>
                  <th>Nama Maestro</th>
                  <th>Keahlian / Kriya</th>
                  <th>Desa</th>
                  <th>Kontak WhatsApp</th>
                  <th>Pengalaman</th>
                  <th>Aksi</th>
                </tr>
              </thead>
              <tbody>
                {#each creators as c}
                  <tr>
                    <td>
                      <img src={c.profileImageUrl} alt={c.name} class="table-thumb rounded-circle" />
                    </td>
                    <td>
                      <strong>{c.name}</strong>
                    </td>
                    <td>
                      <span class="badge badge-forest">{c.craftType}</span>
                    </td>
                    <td>{c.village}</td>
                    <td>
                      <span class="badge badge-verified">✓ {c.whatsappNumber}</span>
                    </td>
                    <td>{c.yearsOfExperience} Tahun</td>
                    <td>
                      <div class="action-buttons">
                        <button class="btn-action btn-edit" on:click={() => openEditModal('kreator', c)} title="Edit">
                          ✏️
                        </button>
                        <button class="btn-action btn-delete" on:click={() => handleDelete('kreator', c.id, c.name)} title="Hapus">
                          🗑️
                        </button>
                      </div>
                    </td>
                  </tr>
                {/each}
              </tbody>
            </table>
          </div>

        <!-- 3. PRODUCTS TABLE -->
        {:else if activeTab === 'produk'}
          <div class="table-responsive">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Foto</th>
                  <th>Nama Produk</th>
                  <th>Kategori</th>
                  <th>Kreator Pembuat</th>
                  <th>Harga</th>
                  <th>Stok</th>
                  <th>Aksi</th>
                </tr>
              </thead>
              <tbody>
                {#each products as p}
                  <tr>
                    <td>
                      <img src={p.imageUrl} alt={p.name} class="table-thumb" />
                    </td>
                    <td>
                      <strong>{p.name}</strong>
                    </td>
                    <td>
                      <span class="badge badge-lake">{p.category}</span>
                    </td>
                    <td>{p.creator?.name || 'Kreator Samosir'}</td>
                    <td><strong>Rp {p.price.toLocaleString('id-ID')}</strong></td>
                    <td>
                      <span class="badge badge-forest">{p.stockStatus}</span>
                    </td>
                    <td>
                      <div class="action-buttons">
                        <button class="btn-action btn-edit" on:click={() => openEditModal('produk', p)} title="Edit">
                          ✏️
                        </button>
                        <button class="btn-action btn-delete" on:click={() => handleDelete('produk', p.id, p.name)} title="Hapus">
                          🗑️
                        </button>
                      </div>
                    </td>
                  </tr>
                {/each}
              </tbody>
            </table>
          </div>

        <!-- 4. CULTURAL STORIES TABLE -->
        {:else if activeTab === 'cerita'}
          <div class="table-responsive">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Sampul</th>
                  <th>Judul Cerita Budaya</th>
                  <th>Kategori</th>
                  <th>Kutipan Batak</th>
                  <th>Waktu Baca</th>
                  <th>Aksi</th>
                </tr>
              </thead>
              <tbody>
                {#each stories as s}
                  <tr>
                    <td>
                      <img src={s.coverImageUrl} alt={s.title} class="table-thumb" />
                    </td>
                    <td>
                      <strong>{s.title}</strong>
                    </td>
                    <td>
                      <span class="badge badge-terracotta">{s.category}</span>
                    </td>
                    <td>
                      <em style="font-size: 0.82rem;">"{s.batakQuote || '-'}"</em>
                    </td>
                    <td>{s.readingTimeMinutes} mnt</td>
                    <td>
                      <div class="action-buttons">
                        <button class="btn-action btn-edit" on:click={() => openEditModal('cerita', s)} title="Edit">
                          ✏️
                        </button>
                        <button class="btn-action btn-delete" on:click={() => handleDelete('cerita', s.id, s.title)} title="Hapus">
                          🗑️
                        </button>
                      </div>
                    </td>
                  </tr>
                {/each}
              </tbody>
            </table>
          </div>

        <!-- 5. EXPERIENCES TABLE -->
        {:else if activeTab === 'agenda'}
          <div class="table-responsive">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Foto</th>
                  <th>Judul Kegiatan</th>
                  <th>Tipe</th>
                  <th>Lokasi</th>
                  <th>Jadwal Pelaksanaan</th>
                  <th>Biaya</th>
                  <th>WhatsApp Booking</th>
                  <th>Aksi</th>
                </tr>
              </thead>
              <tbody>
                {#each experiences as e}
                  <tr>
                    <td>
                      <img src={e.imageUrl} alt={e.title} class="table-thumb" />
                    </td>
                    <td>
                      <strong>{e.title}</strong>
                    </td>
                    <td>
                      <span class="badge badge-forest">{e.type}</span>
                    </td>
                    <td>{e.location}</td>
                    <td>{e.eventDate}</td>
                    <td>{e.price === 0 ? 'Gratis' : 'Rp ' + e.price.toLocaleString('id-ID')}</td>
                    <td>
                      <span class="badge badge-verified">✓ {e.whatsappBooking}</span>
                    </td>
                    <td>
                      <div class="action-buttons">
                        <button class="btn-action btn-edit" on:click={() => openEditModal('agenda', e)} title="Edit">
                          ✏️
                        </button>
                        <button class="btn-action btn-delete" on:click={() => handleDelete('agenda', e.id, e.title)} title="Hapus">
                          🗑️
                        </button>
                      </div>
                    </td>
                  </tr>
                {/each}
              </tbody>
            </table>
          </div>
        {/if}
      {/if}
    </div>
  </div>

  <!-- MODAL CREATE / EDIT -->
  {#if modalOpen}
    <div class="modal-backdrop animate-fade-in" on:click|self={() => modalOpen = false}>
      <div class="modal-dialog card-editorial">
        <div class="modal-header">
          <h3 class="modal-title">
            {modalMode === 'create' ? 'Tambah' : 'Edit'}
            {#if modalEntity === 'destinasi'} Destinasi Wisata
            {:else if modalEntity === 'kreator'} Profil Kreator
            {:else if modalEntity === 'produk'} Produk UMKM
            {:else if modalEntity === 'cerita'} Cerita Budaya Batak
            {:else} Agenda / Pengalaman
            {/if}
          </h3>
          <button class="btn-close" on:click={() => modalOpen = false}>&times;</button>
        </div>

        <div class="modal-body">
          <!-- DESTINASI FORM -->
          {#if modalEntity === 'destinasi'}
            <div class="form-group">
              <label class="form-label">Nama Destinasi</label>
              <input type="text" class="form-input" bind:value={destForm.name} required />
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Kategori</label>
                <select class="form-select" bind:value={destForm.category}>
                  <option value="BUDAYA_SEJARAH">Budaya & Sejarah</option>
                  <option value="ALAM">Panorama Alam</option>
                  <option value="KULINER_UMKM">Kriya & Tenun</option>
                  <option value="PETUALANGAN_RELIGI">Petualangan & Religi</option>
                </select>
              </div>
              <div class="form-group">
                <label class="form-label">Kecamatan</label>
                <select class="form-select" bind:value={destForm.subDistrict}>
                  <option value="Simanindo">Simanindo</option>
                  <option value="Pangururan">Pangururan</option>
                  <option value="Harian">Harian</option>
                  <option value="Sitio-tio">Sitio-tio</option>
                  <option value="Onan Runggu">Onan Runggu</option>
                  <option value="Palipi">Palipi</option>
                </select>
              </div>
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Harga Tiket (Rp)</label>
                <input type="number" class="form-input" bind:value={destForm.ticketPrice} min="0" />
              </div>
              <div class="form-group">
                <label class="form-label">Estimasi Durasi (Jam)</label>
                <input type="number" step="0.5" class="form-input" bind:value={destForm.estimatedDurationHours} min="0.5" />
              </div>
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Latitude</label>
                <input type="number" step="0.0001" class="form-input" bind:value={destForm.latitude} />
              </div>
              <div class="form-group">
                <label class="form-label">Longitude</label>
                <input type="number" step="0.0001" class="form-input" bind:value={destForm.longitude} />
              </div>
            </div>
            <div class="form-group">
              <label class="form-label">URL Foto Utama</label>
              <input type="text" class="form-input" bind:value={destForm.imageUrl} required />
            </div>
            <div class="form-group">
              <label class="form-label">Deskripsi Singkat</label>
              <input type="text" class="form-input" bind:value={destForm.shortDescription} required />
            </div>
            <div class="form-group">
              <label class="form-label">Deskripsi Lengkap</label>
              <textarea rows="3" class="form-textarea" bind:value={destForm.description}></textarea>
            </div>
            <div class="form-group">
              <label class="form-label">Nilai Budaya Batak</label>
              <textarea rows="2" class="form-textarea" bind:value={destForm.culturalSignificance}></textarea>
            </div>
            <div class="form-group">
              <label class="form-label">Fasilitas (pisahkan dengan koma)</label>
              <input type="text" class="form-input" bind:value={destForm.facilities} />
            </div>
            <div class="form-group" style="flex-direction: row; gap: 0.5rem; align-items: center;">
              <input type="checkbox" id="dest-feat" bind:checked={destForm.isFeatured} />
              <label for="dest-feat" style="cursor: pointer; font-size: 0.9rem;">Tandai sebagai Destinasi Unggulan di Beranda</label>
            </div>

          <!-- KREATOR FORM -->
          {:else if modalEntity === 'kreator'}
            <div class="form-group">
              <label class="form-label">Nama Maestro / Perajin</label>
              <input type="text" class="form-input" bind:value={creatorForm.name} required />
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Jenis Keahlian Kriya</label>
                <input type="text" class="form-input" bind:value={creatorForm.craftType} required />
              </div>
              <div class="form-group">
                <label class="form-label">Desa / Kelurahan di Samosir</label>
                <input type="text" class="form-input" bind:value={creatorForm.village} required />
              </div>
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Nomor WhatsApp (+62 / 08)</label>
                <input type="text" class="form-input" bind:value={creatorForm.whatsappNumber} placeholder="+6281234567890" required />
              </div>
              <div class="form-group">
                <label class="form-label">Tahun Pengalaman</label>
                <input type="number" class="form-input" bind:value={creatorForm.yearsOfExperience} min="1" />
              </div>
            </div>
            <div class="form-group">
              <label class="form-label">URL Foto Profil</label>
              <input type="text" class="form-input" bind:value={creatorForm.profileImageUrl} required />
            </div>
            <div class="form-group">
              <label class="form-label">Biografi & Filosofi</label>
              <textarea rows="3" class="form-textarea" bind:value={creatorForm.bio} required></textarea>
            </div>
            <div class="form-group">
              <label class="form-label">Penghargaan / Sertifikasi</label>
              <input type="text" class="form-input" bind:value={creatorForm.awards} />
            </div>

          <!-- PRODUK FORM -->
          {:else if modalEntity === 'produk'}
            <div class="form-group">
              <label class="form-label">Pilih Kreator Pembuat</label>
              <select class="form-select" bind:value={productForm.creatorId}>
                {#each creators as c}
                  <option value={c.id}>{c.name} ({c.craftType} - {c.village})</option>
                {/each}
              </select>
            </div>
            <div class="form-group">
              <label class="form-label">Nama Produk UMKM</label>
              <input type="text" class="form-input" bind:value={productForm.name} required />
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Kategori Produk</label>
                <select class="form-select" bind:value={productForm.category}>
                  <option value="TENUN_ULOS">Tenun Ulos Otentik</option>
                  <option value="KERAJINAN_GORGA">Ukir Kayu Gorga Batak</option>
                  <option value="KOPI_REMPAH">Kopi Arabica Samosir</option>
                  <option value="KULINER_KHAS">Kuliner & Andaliman</option>
                </select>
              </div>
              <div class="form-group">
                <label class="form-label">Harga Produk (Rp)</label>
                <input type="number" class="form-input" bind:value={productForm.price} min="1000" required />
              </div>
            </div>
            <div class="form-group">
              <label class="form-label">URL Foto Produk</label>
              <input type="text" class="form-input" bind:value={productForm.imageUrl} required />
            </div>
            <div class="form-group">
              <label class="form-label">Deskripsi Produk</label>
              <textarea rows="3" class="form-textarea" bind:value={productForm.description} required></textarea>
            </div>

          <!-- CERITA BUDAYA FORM -->
          {:else if modalEntity === 'cerita'}
            <div class="form-group">
              <label class="form-label">Judul Artikel Budaya</label>
              <input type="text" class="form-input" bind:value={storyForm.title} required />
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Kategori</label>
                <select class="form-select" bind:value={storyForm.category}>
                  <option value="FILOSOFI">Falsafah Hidup</option>
                  <option value="LEGENDA">Legenda & Mitos</option>
                  <option value="TRADISI">Tradisi & Wastra</option>
                  <option value="SITUS_SEJARAH">Situs Bersejarah</option>
                </select>
              </div>
              <div class="form-group">
                <label class="form-label">Waktu Baca (Menit)</label>
                <input type="number" class="form-input" bind:value={storyForm.readingTimeMinutes} min="1" />
              </div>
            </div>
            <div class="form-group">
              <label class="form-label">Peribahasa / Kutipan Batak Toba</label>
              <input type="text" class="form-input" bind:value={storyForm.batakQuote} placeholder="Somba marhula-hula..." />
            </div>
            <div class="form-group">
              <label class="form-label">Makna / Terjemahan Kutipan</label>
              <input type="text" class="form-input" bind:value={storyForm.quoteMeaning} />
            </div>
            <div class="form-group">
              <label class="form-label">URL Foto Sampul</label>
              <input type="text" class="form-input" bind:value={storyForm.coverImageUrl} required />
            </div>
            <div class="form-group">
              <label class="form-label">Ringkasan Artikel</label>
              <input type="text" class="form-input" bind:value={storyForm.summary} required />
            </div>
            <div class="form-group">
              <label class="form-label">Konten Lengkap</label>
              <textarea rows="4" class="form-textarea" bind:value={storyForm.content} required></textarea>
            </div>

          <!-- AGENDA FORM -->
          {:else if modalEntity === 'agenda'}
            <div class="form-group">
              <label class="form-label">Judul Kegiatan / Festival</label>
              <input type="text" class="form-input" bind:value={experienceForm.title} required />
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Tipe Kegiatan</label>
                <select class="form-select" bind:value={experienceForm.type}>
                  <option value="WORKSHOP">Workshop Kriya</option>
                  <option value="TUR_BUDAYA">Tur Jelajah Budaya</option>
                  <option value="FESTIVAL_AGENDA">Festival Seni</option>
                  <option value="KULINER">Kelas Kuliner</option>
                </select>
              </div>
              <div class="form-group">
                <label class="form-label">Biaya Pendaftaran (Rp)</label>
                <input type="number" class="form-input" bind:value={experienceForm.price} min="0" />
              </div>
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Lokasi Kegiatan</label>
                <input type="text" class="form-input" bind:value={experienceForm.location} required />
              </div>
              <div class="form-group">
                <label class="form-label">Jadwal Tanggal</label>
                <input type="text" class="form-input" bind:value={experienceForm.eventDate} required />
              </div>
            </div>
            <div class="form-row-2">
              <div class="form-group">
                <label class="form-label">Penyelenggara</label>
                <input type="text" class="form-input" bind:value={experienceForm.organizerName} required />
              </div>
              <div class="form-group">
                <label class="form-label">WhatsApp Booking</label>
                <input type="text" class="form-input" bind:value={experienceForm.whatsappBooking} required />
              </div>
            </div>
            <div class="form-group">
              <label class="form-label">URL Foto Kegiatan</label>
              <input type="text" class="form-input" bind:value={experienceForm.imageUrl} required />
            </div>
            <div class="form-group">
              <label class="form-label">Deskripsi Kegiatan</label>
              <textarea rows="3" class="form-textarea" bind:value={experienceForm.description} required></textarea>
            </div>
          {/if}
        </div>

        <div class="modal-footer">
          <button class="btn btn-secondary btn-sm" on:click={() => modalOpen = false}>Batal</button>
          <button class="btn btn-primary btn-sm" on:click={handleSave}>Simpan Data</button>
        </div>
      </div>
    </div>
  {/if}
</div>

<style>
  .admin-dashboard-page {
    padding-bottom: 5rem;
    background-color: var(--color-ivory);
  }

  .dashboard-topbar {
    background-color: var(--color-forest);
    color: var(--color-ivory);
    padding: 1.75rem 0;
    margin-bottom: 2.5rem;
  }

  .topbar-inner {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .dashboard-title {
    font-size: 1.85rem;
    color: var(--color-ivory);
    margin-bottom: 0.2rem;
  }

  .user-greeting {
    font-size: 0.82rem;
    color: rgba(246, 241, 231, 0.7);
  }

  .topbar-right {
    display: flex;
    align-items: center;
    gap: 0.75rem;
  }

  .metrics-grid {
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    gap: 1.25rem;
    margin-bottom: 2.5rem;
  }

  .metric-card {
    padding: 1.5rem;
    display: flex;
    align-items: center;
    gap: 1rem;
    background-color: var(--color-white);
  }

  .metric-icon {
    font-size: 2rem;
  }

  .metric-info {
    display: flex;
    flex-direction: column;
  }

  .metric-lbl {
    font-size: 0.75rem;
    color: var(--color-muted);
    font-weight: 600;
    text-transform: uppercase;
  }

  .metric-val {
    font-family: var(--font-serif);
    font-size: 1.75rem;
    font-weight: 700;
    color: var(--color-forest);
    line-height: 1.1;
  }

  .module-nav {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
    margin-bottom: 1.5rem;
  }

  .module-btn {
    padding: 0.75rem 1.4rem;
    border-radius: var(--radius-md);
    background-color: var(--color-white);
    border: 1.5px solid var(--color-border);
    font-size: 0.92rem;
    font-weight: 700;
    color: var(--color-forest);
    transition: all var(--transition-fast);
  }

  .module-btn:hover {
    border-color: var(--color-forest);
  }

  .module-active {
    background-color: var(--color-forest) !important;
    color: var(--color-white) !important;
    border-color: var(--color-forest) !important;
    box-shadow: 0 4px 12px rgba(18, 60, 54, 0.18);
  }

  .crud-panel {
    background-color: var(--color-white);
    padding: 2rem;
  }

  .crud-panel-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 1.5rem;
    border-bottom: 1px solid var(--color-border);
    margin-bottom: 1.5rem;
  }

  .panel-title {
    font-size: 1.5rem;
    color: var(--color-forest);
    margin-bottom: 0.25rem;
  }

  .panel-subtitle {
    font-size: 0.85rem;
    color: var(--color-muted);
  }

  .table-responsive {
    overflow-x: auto;
  }

  .data-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 0.9rem;
    text-align: left;
  }

  .data-table th {
    background-color: var(--color-ivory);
    color: var(--color-forest);
    font-weight: 700;
    padding: 0.85rem 1rem;
    border-bottom: 1.5px solid var(--color-border);
  }

  .data-table td {
    padding: 0.85rem 1rem;
    border-bottom: 1px solid var(--color-border);
    color: var(--color-charcoal);
  }

  .data-table tr:hover {
    background-color: var(--color-ivory-light);
  }

  .table-thumb {
    width: 50px;
    height: 45px;
    border-radius: var(--radius-sm);
    object-fit: cover;
  }

  .rounded-circle {
    border-radius: 50% !important;
  }

  .action-buttons {
    display: flex;
    gap: 0.4rem;
  }

  .btn-action {
    padding: 0.35rem 0.6rem;
    border-radius: var(--radius-sm);
    background: var(--color-ivory);
    border: 1px solid var(--color-border);
    cursor: pointer;
    font-size: 0.85rem;
    transition: all var(--transition-fast);
  }

  .btn-action:hover {
    transform: scale(1.1);
  }

  .btn-edit:hover {
    background-color: #E0F2FE;
    border-color: #7DD3FC;
  }

  .btn-delete:hover {
    background-color: #FEE2E2;
    border-color: #F87171;
  }

  /* Modal */
  .modal-backdrop {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(10, 36, 32, 0.7);
    backdrop-filter: blur(4px);
    z-index: 9999;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 1.5rem;
  }

  .modal-dialog {
    width: 100%;
    max-width: 650px;
    max-height: 90vh;
    display: flex;
    flex-direction: column;
    background: var(--color-white);
    padding: 2rem;
  }

  .modal-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1.5rem;
    padding-bottom: 0.75rem;
    border-bottom: 1px solid var(--color-border);
  }

  .modal-title {
    font-size: 1.4rem;
    color: var(--color-forest);
  }

  .btn-close {
    font-size: 1.5rem;
    cursor: pointer;
    color: var(--color-muted);
  }

  .modal-body {
    overflow-y: auto;
    padding-right: 0.5rem;
    display: flex;
    flex-direction: column;
    gap: 0.25rem;
  }

  .modal-footer {
    display: flex;
    justify-content: flex-end;
    gap: 0.75rem;
    margin-top: 1.5rem;
    padding-top: 1rem;
    border-top: 1px solid var(--color-border);
  }

  .form-row-2 {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 1rem;
  }

  .table-loading {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 4rem;
    gap: 1rem;
    color: var(--color-muted);
  }

  .spinner {
    width: 38px;
    height: 38px;
    border: 3px solid var(--color-border);
    border-top-color: var(--color-forest);
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
  }

  @keyframes spin {
    to { transform: rotate(360deg); }
  }

  @media (max-width: 1024px) {
    .metrics-grid {
      grid-template-columns: repeat(3, 1fr);
    }
  }

  @media (max-width: 768px) {
    .metrics-grid {
      grid-template-columns: 1fr 1fr;
    }
    .topbar-inner {
      flex-direction: column;
      align-items: flex-start;
      gap: 1rem;
    }
    .form-row-2 {
      grid-template-columns: 1fr;
    }
  }
</style>
