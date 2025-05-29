"use client"

import React from "react"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { toast } from "sonner"

import { resetPasswordRequest, ResetPasswordRequest } from "@/schemas/auth"
import { resetPassword } from "@/lib/auth"

import { Button } from "../ui/button"
import { Card, CardContent } from "../ui/card"
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

const ForgotPasswordForm: React.FC = () => {
  const form = useForm<ResetPasswordRequest>({
    defaultValues: {
      email: "",
    },
    resolver: zodResolver(resetPasswordRequest),
  })

  async function onSubmit(values: ResetPasswordRequest) {
    const success = await resetPassword(values)
    if (success) {
      toast.success("Password reset email sent successfully.")
    }
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)}>
        <Card className="border-none bg-transparent shadow-none">
          <CardContent>
            <div className="space-y-6">
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email Address</FormLabel>
                    <FormControl>
                      <Input
                        type="email"
                        placeholder="Email Address"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <Button
                type="submit"
                className="w-full"
                disabled={form.formState.isSubmitting}
              >
                {form.formState.isSubmitting ? <LoadingSpinner /> : "Send"}
              </Button>
            </div>
          </CardContent>
        </Card>
      </form>
    </Form>
  )
}
export default ForgotPasswordForm
