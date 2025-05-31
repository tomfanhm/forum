import {
  ProfileResponse,
  profileResponse,
  UpdateProfileRequest,
} from "@/schemas/user"

import client from "./client"
import { showErrorToast } from "./utils"

export const getProfile = async (): Promise<ProfileResponse> => {
  try {
    const response = await client.get("/user/profile")
    return profileResponse.parse(response.data)
  } catch (error) {
    showErrorToast(error)
    throw error
  }
}

export const updateProfile = async (
  request: UpdateProfileRequest
): Promise<ProfileResponse> => {
  try {
    const response = await client.put("/user/profile", {
      ...request,
      birthday: request.birthday
        ? new Date(request.birthday).toISOString().split("T")[0] // Format to YYYY-MM-DD
        : undefined,
    })
    return profileResponse.parse(response.data)
  } catch (error) {
    showErrorToast(error)
    throw error
  }
}
