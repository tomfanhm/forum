import axios from "axios"
import { clsx, type ClassValue } from "clsx"
import { toast } from "sonner"
import { twMerge } from "tailwind-merge"
import { ZodError } from "zod"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function showErrorToast(
  error: unknown,
  message: string = "An unknown error occurred."
) {
  if (error instanceof ZodError) {
    const message = error.errors.map((err) => err.message).join(", ")
    return toast.error(message)
  }

  if (axios.isAxiosError(error)) {
    if (error.response?.data?.message) {
      return toast.error(error.response.data.message)
    } else if (error.message) {
      return toast.error(error.message)
    } else {
      return toast.error("Network request failed.")
    }
  }

  if (error instanceof Error) {
    return toast.error(error.message)
  }

  toast.error(message)
}
