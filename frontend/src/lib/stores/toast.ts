import { writable } from 'svelte/store';

export interface ToastMessage {
  id: string;
  type: 'success' | 'error' | 'info';
  message: string;
}

export const toasts = writable<ToastMessage[]>([]);

export function showToast(message: string, type: 'success' | 'error' | 'info' = 'info') {
  const id = Math.random().toString(36).substring(2, 9);
  toasts.update(current => [...current, { id, type, message }]);

  setTimeout(() => {
    removeToast(id);
  }, 4000);
}

export function removeToast(id: string) {
  toasts.update(current => current.filter(t => t.id !== id));
}
