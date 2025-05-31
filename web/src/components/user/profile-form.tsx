"use client"

import React from "react"
import { zodResolver } from "@hookform/resolvers/zod"
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query"
import { format } from "date-fns"
import { CalendarIcon } from "lucide-react"
import { useForm } from "react-hook-form"
import { toast } from "sonner"

import {
  Gender,
  ProfileResponse,
  updateProfileRequest,
  UpdateProfileRequest,
} from "@/schemas/user"
import { getProfile, updateProfile } from "@/lib/user"
import { cn } from "@/lib/utils"
import { useAuthStore } from "@/stores/use-auth-store"

import DashboardFallback from "../dashboard-fallback"
import DashboardLoading from "../dashboard-loading"
import { Button } from "../ui/button"
import { Calendar } from "../ui/calendar"
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "../ui/form"
import { Input } from "../ui/input"
import { LoadingSpinner } from "../ui/loading-spinner"
import { Popover, PopoverContent, PopoverTrigger } from "../ui/popover"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select"
import { Textarea } from "../ui/textarea"

const GENDER_OPTIONS: Array<{
  value: Gender
  label: string
}> = [
  { value: "MALE", label: "Male" },
  { value: "FEMALE", label: "Female" },
  { value: "OTHER", label: "Other" },
]

const ProfileForm: React.FC = () => {
  const { auth } = useAuthStore()
  const queryClient = useQueryClient()

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["profile", auth!.email],
    queryFn: async (): Promise<ProfileResponse> => {
      return await getProfile()
    },
    retry: 3,
    refetchOnWindowFocus: false,
  })

  const updateMutation = useMutation({
    mutationFn: async (values: UpdateProfileRequest) => {
      return await updateProfile(values)
    },
    onSuccess: (data) => {
      toast.success("Profile updated.")
      queryClient.setQueryData(["profile", auth!.email], data)
    },
  })

  if (isLoading) return <DashboardLoading />
  if (error && isError)
    return <DashboardFallback title={error.name} description={error.message} />
  if (!data)
    return (
      <DashboardFallback
        title="No profile found"
        description="Please try again later."
      />
    )

  return (
    <ProfileFormView
      defaultValues={data}
      onSubmit={(values: UpdateProfileRequest) => updateMutation.mutate(values)}
      isSubmitting={updateMutation.isPending}
    />
  )
}

type ProfileFormViewProps = {
  defaultValues: ProfileResponse
  onSubmit: (values: UpdateProfileRequest) => void
  isSubmitting: boolean
}

const ProfileFormView: React.FC<ProfileFormViewProps> = ({
  defaultValues,
  onSubmit,
  isSubmitting,
}) => {
  const form = useForm<UpdateProfileRequest>({
    defaultValues: {
      display_name: defaultValues.display_name || undefined,
      avatar_url: defaultValues.avatar_url || undefined,
      birthday: defaultValues.birthday ? defaultValues.birthday : undefined,
      gender: defaultValues.gender || undefined,
      bio: defaultValues.bio || undefined,
      location: defaultValues.location || undefined,
      website: defaultValues.website || undefined,
    },
    resolver: zodResolver(updateProfileRequest),
  })

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)}>
        {/* Inputs */}
        <div>
          <h2 className="text-foreground text-base font-semibold">
            Personal Details
          </h2>
          <p className="text-muted-foreground mt-1 max-w-2xl text-sm">
            Tell us about yourself
          </p>
          <div className="border-border sm:divide-border mt-10 space-y-8 border-b pb-12 sm:space-y-0 sm:divide-y sm:border-t sm:pb-0">
            <FormField
              control={form.control}
              name="display_name"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Display Name</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <FormControl>
                      <Input
                        type="text"
                        placeholder="Display Name"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="avatar_url"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Avatar URL</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <FormControl>
                      <Input
                        type="text"
                        placeholder="https://example.com/avatar.png"
                        {...field}
                        readOnly
                      />
                    </FormControl>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="birthday"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Birthday</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <Popover>
                      <PopoverTrigger asChild>
                        <FormControl>
                          <Button
                            variant="outline"
                            className={cn(
                              "w-full justify-start text-left",
                              !field.value && "text-muted-foreground"
                            )}
                          >
                            {field.value ? (
                              format(field.value, "PPP")
                            ) : (
                              <span>Select a date</span>
                            )}
                            <CalendarIcon className="ml-auto" />
                          </Button>
                        </FormControl>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0" align="start">
                        <Calendar
                          mode="single"
                          selected={
                            field.value ? new Date(field.value) : undefined
                          }
                          onSelect={field.onChange}
                          initialFocus
                          disabled={
                            (date) => date > new Date() // Disable future dates
                          }
                          captionLayout="dropdown-buttons"
                          fromYear={new Date().getFullYear()}
                          toYear={new Date().getFullYear() + 1}
                        />
                      </PopoverContent>
                    </Popover>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="gender"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Gender</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <Select
                      onValueChange={field.onChange}
                      defaultValue={field.value}
                    >
                      <FormControl>
                        <SelectTrigger className="w-full">
                          <SelectValue placeholder="Select gender" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        {GENDER_OPTIONS.map((option) => (
                          <SelectItem key={option.value} value={option.value}>
                            <span>{option.label}</span>
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="bio"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Bio</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <FormControl>
                      <Textarea
                        rows={5}
                        placeholder="Tell us about yourself"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="location"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Location</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <FormControl>
                      <Input
                        type="text"
                        placeholder="City, Country"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="website"
              render={({ field }) => (
                <FormItem className="sm:grid sm:grid-cols-3 sm:items-start sm:gap-4 sm:py-6">
                  <FormLabel>Website</FormLabel>
                  <div className="mt-2 sm:col-span-2 sm:mt-0">
                    <FormControl>
                      <Input
                        type="text"
                        placeholder="https://example.com"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
          </div>
        </div>
        {/* Button */}
        <div className="mt-6 flex justify-end">
          <Button
            type="submit"
            disabled={isSubmitting}
            className="w-full sm:w-auto"
          >
            {isSubmitting ? <LoadingSpinner /> : "Confirm"}
          </Button>
        </div>
      </form>
    </Form>
  )
}

export default ProfileForm
