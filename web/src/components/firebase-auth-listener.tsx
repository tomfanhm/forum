"use client"

import React, { useEffect } from "react"
import { onAuthStateChanged, User } from "firebase/auth"

import { authResponse } from "@/schemas/auth"
import client from "@/lib/client"
import { auth } from "@/lib/firebase/config"
import { useAuthStore } from "@/stores/use-auth-store"

const FirebaseAuthListener: React.FC = () => {
  const { setAuth, setLoading, destroy } = useAuthStore()

  useEffect(() => {
    const unsubscribe = onAuthStateChanged(auth, async (user: User | null) => {
      setLoading(true)
      try {
        if (user) {
          const idToken = await user.getIdToken()

          const response = await client.post("/auth/login", {
            id_token: idToken,
          })

          if (response.status === 200) {
            const auth = authResponse.parse(response.data)
            setAuth(auth)
          }
        } else {
          destroy()
        }
      } catch {
        // Do nothing
      } finally {
        setLoading(false)
      }
    })

    return () => unsubscribe()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return null
}
export default FirebaseAuthListener
