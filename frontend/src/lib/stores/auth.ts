import { writable } from 'svelte/store';
import type { AuthResponse } from '../types';

interface AuthState {
  isAuthenticated: boolean;
  user: AuthResponse | null;
  token: string | null;
}

const initialToken = typeof window !== 'undefined' ? localStorage.getItem('samosirverse_token') : null;
const initialUserJson = typeof window !== 'undefined' ? localStorage.getItem('samosirverse_user') : null;
let initialUser: AuthResponse | null = null;
try {
  if (initialUserJson) initialUser = JSON.parse(initialUserJson);
} catch {
  // Ignore parse error
}

export const authStore = writable<AuthState>({
  isAuthenticated: !!initialToken,
  user: initialUser,
  token: initialToken
});

export function setSession(authData: AuthResponse) {
  if (typeof window !== 'undefined') {
    localStorage.setItem('samosirverse_token', authData.token);
    localStorage.setItem('samosirverse_user', JSON.stringify(authData));
  }
  authStore.set({
    isAuthenticated: true,
    user: authData,
    token: authData.token
  });
}

export function clearSession() {
  if (typeof window !== 'undefined') {
    localStorage.removeItem('samosirverse_token');
    localStorage.removeItem('samosirverse_user');
  }
  authStore.set({
    isAuthenticated: false,
    user: null,
    token: null
  });
}
