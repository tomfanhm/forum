import { getDownloadURL, ref, uploadBytes } from "firebase/storage"

import { auth, storage } from "./config"

export const uploadImage = async (
  file: File,
  filePath: string
): Promise<string> => {
  const uid = auth.currentUser?.uid
  if (!uid) throw new Error("User not authenticated.")
  const fileRef = ref(storage, `${uid}/${filePath}`)
  await uploadBytes(fileRef, file)
  const downloadURL = await getDownloadURL(fileRef)
  return downloadURL
}
