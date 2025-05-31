import z from "zod"

export const communityType = z.enum(["PUBLIC", "PRIVATE"])
export type CommunityType = z.infer<typeof communityType>

export const communityInformation = z.object({
  id: z.string().uuid(),
  name: z.string().min(1),
  description: z.string().nullable(),
  banner_url: z.string().url().nullable(),
  icon_url: z.string().url().nullable(),
  type: communityType,
  is_mature: z.boolean(),
  members_count: z.number().int().nonnegative(),
  created_at: z
    .string()
    .datetime()
    .transform((val) => new Date(val)),
  topics: z.string().array().min(1).max(5),
  author_id: z.string(),
  author_display_name: z.string().nullable(),
  author_avatar_url: z.string().url().nullable(),
})
export type CommunityInformation = z.infer<typeof communityInformation>

export const createCommunityRequest = z.object({
  name: z
    .string()
    .trim()
    .min(1, { message: "Community name must be at least 1 character." })
    .max(255, { message: "Community name must be at most 255 characters." }),
  description: z
    .string()
    .max(1000, { message: "Description must be at most 1000 characters." })
    .optional(),
  banner_url: z
    .string()
    .url({ message: "Banner URL must be a valid URL" })
    .max(2048, { message: "Banner URL is too long" })
    .optional(),
  icon_url: z
    .string()
    .url({ message: "Icon URL must be a valid URL" })
    .max(2048, { message: "Icon URL is too long" })
    .optional(),
  type: communityType,
  is_mature: z.boolean().default(false),
  topics: z
    .array(
      z
        .string()
        .trim()
        .min(1, { message: "Topic cannot be empty" })
        .max(30, { message: "Each topic must be at most 30 characters" })
    )
    .min(1, { message: "You must provide at least 1 topic" })
    .max(5, { message: "You can only have up to 5 topics" }),
})
export type CreateCommunityRequest = z.infer<typeof createCommunityRequest>

export const updateCommunityInformationRequest = createCommunityRequest.omit({
  name: true, // Name cannot be updated
})
export type UpdateCommunityInformationRequest = z.infer<
  typeof updateCommunityInformationRequest
>
