<script lang="ts">
  import { toasts, removeToast } from '$lib/stores/toast';
</script>

<div class="toast-container" aria-live="polite">
  {#each $toasts as toast (toast.id)}
    <div class="toast-item toast-{toast.type} animate-fade-in">
      <div class="toast-icon">
        {#if toast.type === 'success'}
          ✅
        {:else if toast.type === 'error'}
          ⚠️
        {:else}
          ℹ️
        {/if}
      </div>
      <div class="toast-text">{toast.message}</div>
      <button class="toast-close" on:click={() => removeToast(toast.id)} aria-label="Tutup">
        &times;
      </button>
    </div>
  {/each}
</div>

<style>
  .toast-container {
    position: fixed;
    bottom: 1.5rem;
    right: 1.5rem;
    z-index: 9999;
    display: flex;
    flex-direction: column;
    gap: 0.75rem;
    max-width: 420px;
    width: calc(100% - 3rem);
    pointer-events: none;
  }

  .toast-item {
    pointer-events: auto;
    display: flex;
    align-items: center;
    gap: 0.75rem;
    padding: 0.85rem 1.15rem;
    border-radius: var(--radius-md);
    background-color: var(--color-white);
    box-shadow: 0 10px 30px rgba(18, 60, 54, 0.18);
    border: 1px solid var(--color-border);
    font-size: 0.88rem;
    font-weight: 500;
  }

  .toast-success {
    border-left: 4px solid var(--color-success);
  }
  .toast-error {
    border-left: 4px solid var(--color-danger);
  }
  .toast-info {
    border-left: 4px solid var(--color-lake-dark);
  }

  .toast-icon {
    font-size: 1.1rem;
    flex-shrink: 0;
  }

  .toast-text {
    flex: 1;
    color: var(--color-charcoal);
  }

  .toast-close {
    font-size: 1.25rem;
    line-height: 1;
    color: var(--color-muted);
    background: none;
    border: none;
    cursor: pointer;
    padding: 0 0.25rem;
  }

  .toast-close:hover {
    color: var(--color-charcoal);
  }
</style>
