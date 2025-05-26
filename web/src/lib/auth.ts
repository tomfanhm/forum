import {
  createUserWithEmailAndPassword,
  GoogleAuthProvider,
  signInWithEmailAndPassword,
  signInWithPopup,
} from "firebase/auth"

import {
  authResponse,
  AuthResponse,
  LoginRequest,
  RegisterRequest,
} from "@/schemas/auth"

import client from "./client"
import { auth } from "./firebase/config"
import { showErrorToast } from "./utils"

export const register = async (request: RegisterRequest): Promise<boolean> => {
  try {
    // Create a new user with email and password
    const userCredential = await createUserWithEmailAndPassword(
      auth,
      request.email,
      request.password
    )
    // Send the ID token to backend for verification and user creation
    const idToken = await userCredential.user.getIdToken()

    const response = await client.post("/auth/register", {
      id_token: idToken,
    })

    if (response.status === 200) {
      return true
    }
  } catch (error: unknown) {
    showErrorToast(error)
  }
  return false
}

export const login = async (
  request: LoginRequest
): Promise<AuthResponse | null> => {
  try {
    // Sign in with email and password
    const userCredential = await signInWithEmailAndPassword(
      auth,
      request.email,
      request.password
    )
    // Send the ID token to backend for verification
    const idToken = await userCredential.user.getIdToken()

    const response = await client.post("/auth/login", {
      id_token: idToken,
    })

    if (response.status === 200) {
      const data = response.data
      return authResponse.parse(data)
    }
  } catch (error: unknown) {
    showErrorToast(error)
  }
  return null
}

export const loginWithGoogle = async (): Promise<AuthResponse | null> => {
  try {
    // Sign in with Google
    const provider = new GoogleAuthProvider()
    const result = await signInWithPopup(auth, provider)
    const idToken = await result.user.getIdToken()
    // Send the ID token to backend for verification and user creation if needed
    const response = await client.post("/auth/login", {
      id_token: idToken,
    })

    if (response.status === 200) {
      const data = response.data
      return authResponse.parse(data)
    }
  } catch (error: unknown) {
    showErrorToast(error)
  }
  return null
}
