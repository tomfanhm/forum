import { create } from "zustand"

import { AuthResponse } from "@/schemas/auth"

export type Auth = AuthResponse

type AuthStore = {
  auth: Auth | null
  loading: boolean
  setAuth: (auth: Auth) => void
  setLoading: (loading: boolean) => void
  destroy: () => void
}

export const useAuthStore = create<AuthStore>((set) => ({
  auth: null,
  loading: true, // Set loading to true by default, set it to false when the auth state is determined
  setAuth: (auth: Auth) => set({ auth }),
  setLoading: (loading: boolean) => set({ loading }),
  destroy: () => set({ auth: null }),
}))
