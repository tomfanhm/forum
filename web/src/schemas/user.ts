import z from "zod"

export const gender = z.enum(["MALE", "FEMALE", "OTHER"])
export type Gender = z.infer<typeof gender>

export const updateProfileRequest = z.object({
  display_name: z
    .string()
    .max(255, { message: "Display name must be at most 255 characters" })
    .optional(),
  avatar_url: z
    .string()
    .url({ message: "Avatar URL must be a valid URL" })
    .max(2048, { message: "Avatar URL is too long" })
    .optional(),
  birthday: z.date().optional(),
  gender: gender.optional(),
  bio: z
    .string()
    .max(3000, { message: "Bio must be at most 3000 characters" })
    .optional(),
  location: z
    .string()
    .max(255, { message: "Location must be at most 255 characters" })
    .optional(),
  website: z
    .string()
    .url({ message: "Website URL must be a valid URL" })
    .max(2048, { message: "Website URL is too long" })
    .optional(),
})
export type UpdateProfileRequest = z.infer<typeof updateProfileRequest>

export const profileResponse = z.object({
  display_name: z.string().nullable(),
  avatar_url: z.string().url().nullable(),
  bio: z.string().nullable(),
  website: z.string().url().nullable(),
  location: z.string().nullable(),
  birthday: z.preprocess(
    (val) => (typeof val === "string" ? new Date(val) : val),
    z.date().nullable()
  ),
  gender: gender.nullable(),
})
export type ProfileResponse = z.infer<typeof profileResponse>
