import { z } from "zod"

export const email = z.string().email({
  message: "Invalid email address.",
})

export type Email = z.infer<typeof email>

export const password = z
  .string()
  .min(8, { message: "Password must be at least 8 characters long." })
  .regex(/[A-Z]/, {
    message: "Password must contain at least one uppercase letter.",
  })
  .regex(/[a-z]/, {
    message: "Password must contain at least one lowercase letter.",
  })
  .regex(/[0-9]/, { message: "Password must contain at least one number." })
  .regex(/[\W_]/, {
    message: "Password must contain at least one special character.",
  })

export type Password = z.infer<typeof password>

export const registerRequest = z
  .object({
    email,
    password,
    confirm_password: password,
  })
  .refine((data) => data.password === data.confirm_password, {
    message: "Passwords don't match.",
  })

export type RegisterRequest = z.infer<typeof registerRequest>

export const loginRequest = z.object({
  email,
  password,
})

export type LoginRequest = z.infer<typeof loginRequest>

export const resetPasswordRequest = z.object({
  email,
})

export type ResetPasswordRequest = z.infer<typeof resetPasswordRequest>

export const changePasswordRequest = z
  .object({
    current_password: password,
    new_password: password,
    confirm_new_password: password,
  })
  .refine((data) => data.new_password === data.confirm_new_password, {
    message: "Passwords don't match.",
  })

export type ChangePasswordRequest = z.infer<typeof changePasswordRequest>

export const authResponse = z.object({
  email,
  display_name: z.string().nullable(),
  avatar_url: z.string().nullable(),
  roles: z.array(z.string()),
})

export type AuthResponse = z.infer<typeof authResponse>
