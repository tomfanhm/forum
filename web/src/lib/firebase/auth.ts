import {
  onAuthStateChanged as _onAuthStateChanged,
  onIdTokenChanged as _onIdTokenChanged,
  GoogleAuthProvider,
  signInWithPopup,
  User,
  UserCredential,
} from "firebase/auth"

import { auth } from "./config"

export function onAuthStateChanged(callback: (user: User | null) => void) {
  return _onAuthStateChanged(auth, callback)
}

export function onIdTokenChanged(callback: (user: User | null) => void) {
  return _onIdTokenChanged(auth, callback)
}

export async function signInWithGoogle(): Promise<UserCredential | undefined> {
  const provider = new GoogleAuthProvider()

  try {
    return await signInWithPopup(auth, provider)
  } catch (error) {
    console.error("Error signing in with Google", error)
  }
}

export async function signOut() {
  try {
    return auth.signOut()
  } catch (error) {
    console.error("Error signing out", error)
  }
}
